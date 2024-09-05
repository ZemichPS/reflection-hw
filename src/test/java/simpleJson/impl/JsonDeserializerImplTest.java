package simpleJson.impl;

import model.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import simpleJson.impl.typedes.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class JsonDeserializerImplTest {

    private JsonDeserializerImpl jsonDeserializer;


    @BeforeEach
    void setUp() {
        jsonDeserializer = new JsonDeserializerImpl(
                new JsonParserImpl(),
                List.of(
                        new BooleanDeserializer(),
                        new StringDeserializer(),
                        new IntegerDeserializer(),
                        new DoubleDeserializer(),
                        new UUIDDeserializer()
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getJsonSource")
    void deserialize(String json) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        ProductDto productDto = jsonDeserializer.deserialize(json, ProductDto.class);

        assertAll(
                () -> assertNotNull(productDto),
                () -> assertEquals(98765, productDto.getSupplier().getId()),
                () -> assertEquals(5, productDto.getAvailableFunctions().size())
        );

    }

    @Test
    void addJsonTypeDeserializer() {
        JsonDeserializerImpl jsonDeserializer = new JsonDeserializerImpl(new JsonParserImpl(), List.of());
        assertDoesNotThrow(()-> jsonDeserializer.addJsonTypeDeserializer(new IntegerDeserializer()));
    }

    private static Stream<Arguments> getJsonSource() {
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
                         }
                        """
                )
        );
    }
}