package simpleJson.impl.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import simpleJson.exception.InvalidJsonException;

import static org.junit.jupiter.api.Assertions.*;

class NotNullHandlerTest {
    private NotNullHandler handler = new NotNullHandler();

    @ParameterizedTest
    @DisplayName("Throw InvalidJsonException.class if object source is null or invalid")
    @NullSource
    void handle(String nullSource) {

        Assertions.assertAll(
                () -> assertThrows(InvalidJsonException.class,
                        ()-> handler.handle(nullSource, new NullObjectHandler())),
                ()-> assertDoesNotThrow(()->handler.handle("{}", new NullObjectHandler()))
        );
    }
}
