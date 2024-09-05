package simpleJson.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjectNodeTest {

    private ObjectNode objectNode;

    @BeforeEach
    void setUp() {
        objectNode = new ObjectNode("root");
    }

    @Test
    void addNode() {
        ValueNode node1 = new ValueNode("test1");
        objectNode.addNode("child", node1);

        assertAll(
                () -> assertNotNull(objectNode.getChildren()),
                () -> assertEquals(1, objectNode.getChildren().size())
        );
    }

    @Test
    void getNodeByName() {
        ValueNode node1 = new ValueNode("test1");
        objectNode.addNode("child", node1);
        assertEquals(node1, objectNode.getNodeByName("child").get());
    }

    @Test
    void getChildren() {
        ValueNode node1 = new ValueNode("test1");
        objectNode.addNode("child", node1);
        assertNotNull(objectNode.getChildren());
    }

    @Test
    void getName() {
        assertAll(
                () -> assertNotNull(objectNode.getName()),
                () -> assertEquals("root", objectNode.getName())
        );

    }

    @Test
    void setName() {
        objectNode.setName("parent");
        assertAll(
                () -> assertNotNull(objectNode.getName()),
                () -> assertEquals("parent", objectNode.getName())
        );
    }

    @Test
    void testToString() {
        ValueNode node1 = new ValueNode("test1");
        objectNode.addNode("child", node1);
        assertEquals("root:{child=test1}", objectNode.toString());
    }
}