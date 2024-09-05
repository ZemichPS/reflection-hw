package simpleJson.impl.typedes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StringDeserializerTest {

    StringDeserializer deserializer = new StringDeserializer();

    @Test
    void parseObject() {
        Object value = "value";
        assertInstanceOf(String.class, deserializer.parseObject(value));
    }

    @Test
    void getType() {
        assertEquals(String.class.getTypeName(), deserializer.getType());
    }
}