package simpleJson.impl.typedes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UUIDDeserializerTest {

    UUIDDeserializer deserializer = new UUIDDeserializer();

    @Test
    void parseObject() {
        Object value = "4b6b2477-b94d-4ca2-b532-6e34eb91765a";
        assertInstanceOf(UUID.class, deserializer.parseObject(value));
    }

    @Test
    void getType() {
        assertEquals(UUID.class.getTypeName(), deserializer.getType());
    }
}