package simpleJson.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayNodeTest {

    ArrayNode arrayNode = null;

    @BeforeEach
    void setUp() {
         arrayNode = new ArrayNode("testList");
    }

    @Test
    void addNode() {
        ValueNode node1 = new ValueNode("test1");
        ValueNode node2 = new ValueNode("test2");
        arrayNode.addNode(node1);
        arrayNode.addNode(node2);

        assertAll(
                () -> assertNotNull(arrayNode.getChildNodes()),
                () -> assertEquals(2, arrayNode.getChildNodes().size())
        );
    }

    @Test
    void getChildNodes() {
        ValueNode node1 = new ValueNode("test1");
        ValueNode node2 = new ValueNode("test2");
        arrayNode.addNode(node1);
        arrayNode.addNode(node2);
        ValueNode valueNode = (ValueNode) arrayNode.getChildNodes().get(0);

        assertAll(
                () -> assertNotNull(arrayNode.getChildNodes()),
                () -> assertEquals(2, arrayNode.getChildNodes().size()),
                () -> assertEquals("test1", String.valueOf(valueNode.getValue()))
        );
    }

    @Test
    void getName() {
        assertEquals("testList", arrayNode.getName());

    }

    @Test
    void testToString() {
        ValueNode node1 = new ValueNode("test1");
        arrayNode.addNode(node1);
        assertEquals("[test1]", arrayNode.toString());
    }
}