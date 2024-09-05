package simpleJson.impl.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import simpleJson.exception.InvalidJsonException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InvalidStructureHandlerTest {

    private InvalidStructureHandler handler;

    @BeforeEach
    void setup() {
        handler = new InvalidStructureHandler();
    }

    @ParameterizedTest
    @DisplayName("Throw InvalidJsonException if json structure is invalid")
    @MethodSource("jsonDataProvider")
    void handle(String invalidJson, String validJson) throws InvalidJsonException {
        Assertions.assertAll(
                () -> assertThrows(InvalidJsonException.class, () -> handler.handle(invalidJson, new NullObjectHandler())),
                () -> assertDoesNotThrow(() -> handler.handle(validJson, new NullObjectHandler()))
        );
    }

    private static Stream<Arguments> jsonDataProvider() {
        return Stream.of(
                Arguments.of(
                        """
                        {
                           "uuid": "123e4567-e89b-12d3-a456-426614174000",
                           "name": "Smartphone",
                           "description": "Latest model smartphone with advanced features",
                           "price$": 799.99,
                           "sale": true,
                           "supplier": {
                             "id": 98765,
                             "name": "Tech Supplier Inc.",
                             "description": "Leading supplier of electronic devices"
                           },
                           "availableFunctions": [
                             "Bluetooth",
                             "WiFi",
                             "GPS",
                             "NFC",
                             "Face Recognition"
                           ]
                        """,
                        """
                        {
                           "uuid": "123e4567-e89b-12d3-a456-426614174000",
                           "name": "Smartphone",
                           "description": "Latest model smartphone with advanced features",
                           "price": 799.99,
                           "sale": true
                        }
                        """
                )
        );
    }

}