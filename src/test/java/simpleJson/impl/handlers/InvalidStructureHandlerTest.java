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

import static org.junit.jupiter.api.Assertions.*;

class InvalidStructureHandlerTest {

    private InvalidStructureHandler handler;

    @BeforeEach
    void setup() {
        handler = new InvalidStructureHandler();
    }

    @ParameterizedTest
    @DisplayName("Throw InvalidJsonException.class if json structure invalid")
    @MethodSource("getJson")
    void handle(String json) throws InvalidJsonException {
        Assertions.assertAll(
                () -> Assertions.assertThrows(InvalidJsonException.class, ()-> handler.handle(json, new NullObjectHandler()))
        );
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
                    "supplier": {
                      "id": 98765,
                      "name": "Tech Supplier Inc.",
                      "description": "Leading supplier of electronic devices"
                    },
                    "inBox": [
                      {
                        "id": 1,
                        "name": "Charger",
                        "description": "Fast charging adapter"
                      },
                      {
                        "id": 2,
                        "name": "Earphones",
                        "description": "High-quality in-ear headphones"
                      },
                      {
                        "id": 3,
                        "name": "USB Cable",
                        "description": "USB Type-C charging cable"
                      }
                    ],
                    "availableFunctions": [
                      "Bluetooth",
                      "WiFi",
                      "GPS",
                      "NFC",
                      "Face Recognition"
                    ]
                """));
    }

}