package simpleJson.impl.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceImplTest {

    private ValidationServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new ValidationServiceImpl();

    }

    @ParameterizedTest
    @MethodSource("jsonDataProvider")
    void validate(String validJson, String inValidJson) {
        service.validate(validJson);
        service.validate(inValidJson);
        System.out.println(inValidJson);
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
                                """
                )
        );
    }
}