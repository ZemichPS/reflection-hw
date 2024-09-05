package simpleJson.impl.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NullObjectHandlerTest {

    private NullObjectHandler handler;

    @BeforeEach
    void setup() {
        handler = new NullObjectHandler();
    }

    @ParameterizedTest
    @DisplayName("Doesn't throw any Exception even json structure is invalid")
    @ValueSource(strings = {"{ ]"})
    void handle(String invalidJson) {

        Assertions.assertAll(
                () -> assertDoesNotThrow(() -> handler.handle(invalidJson, new NullObjectHandler()))
        );
    }
}