package simpleJson.impl.typedes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BooleanDeserializerTest {

    BooleanDeserializer deserializer = new BooleanDeserializer();

    @Test
    void parseObject() {
        Object value = true;
        assertInstanceOf(Boolean.class, deserializer.parseObject(value));
    }

    @Test
    void getType() {
        assertEquals(boolean.class.getSimpleName(), deserializer.getType());
    }
}