package simpleJson.impl.typedes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class IntegerDeserializerTest {

    IntegerDeserializer deserializer = new IntegerDeserializer();

    @Test
    void parseObject() {
        Object value = 74;
        assertInstanceOf(Integer.class, deserializer.parseObject(value));
    }

    @Test
    void getType() {
        assertEquals(Integer.class.getTypeName(), deserializer.getType());
    }
}