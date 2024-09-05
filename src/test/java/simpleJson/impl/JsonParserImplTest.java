package simpleJson.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import simpleJson.structure.Node;
import simpleJson.structure.ObjectNode;
import simpleJson.structure.ValueNode;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserImplTest {

    private JsonParserImpl parser = new JsonParserImpl();

    @ParameterizedTest
    @MethodSource("getJsonSource")
    void parse(String json) {
        ObjectNode parrent = parser.parse(json);
        ValueNode member = (ValueNode) parrent.getNodeByName("name").orElse(new ValueNode("iphone"));

        assertAll(
                () -> assertNotNull(parser.parse(json)),
                () -> assertEquals("Smartphone", member.getValue())
        );
    }

    @Test
    void checkAndTryToHandlePrimitive() {
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