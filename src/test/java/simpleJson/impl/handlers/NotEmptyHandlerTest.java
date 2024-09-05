package simpleJson.impl.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import simpleJson.exception.InvalidJsonException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NotEmptyHandlerTest {

    private NotEmptyHandler handler;

    @Test
    void handle() {
    }

    @BeforeEach
    void setup() {
        handler = new NotEmptyHandler();
    }

    @ParameterizedTest
    @DisplayName("Throw InvalidJsonException.class if validJson structure invalid")
    @MethodSource("getJson")
    void handle(String validJson) throws InvalidJsonException {
        String empty = "";
        Assertions.assertAll(
                () -> assertThrows(InvalidJsonException.class,
                        ()-> handler.handle(empty, new NullObjectHandler())),
                ()-> assertDoesNotThrow(()-> handler.handle(validJson, new NullObjectHandler())));
    };

    private static Stream<Arguments> getJson() {
        return Stream.of(
                Arguments.of("""
                 {
                    "uuid": "123e4567-e89b-12d3-a456-426614174000",
                    "name": "Smartphone",
                    "description": "Latest model smartphone with advanced features",
                    "price$": 799.99,
                    "sale": true,
                 }
                """));
    }
}