package simpleJson.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValueNodeTest {


    ValueNode valueNode = null;

    @BeforeEach
    void setUp() {
        valueNode = new ValueNode("child");
    }

    @Test
    void getValue() {
        assertEquals("child", valueNode.getValue());
    }

    @Test
    void testToString() {
        assertEquals("child", valueNode.toString());
    }
}