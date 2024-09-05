package simpleJson.impl.typedes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DoubleDeserializerTest {

    DoubleDeserializer deserializer = new DoubleDeserializer();

    @Test
    void parseObject() {
        Object value = 7.15;
        assertInstanceOf(Double.class, deserializer.parseObject(value));
    }

    @Test
    void getType() {
        assertEquals(Double.class.getTypeName(), deserializer.getType());
    }
}