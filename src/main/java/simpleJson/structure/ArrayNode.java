package simpleJson.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayNode extends Node{
    private List<Node> childNodes = new ArrayList<>();
    private String name;

    public ArrayNode(String name) {
        this.name = name;
    }

    public void addNode(final Node node) {
        childNodes.add(node);
    }

    private List<Node> getChildNodes() {
        return new ArrayList<>(childNodes);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return childNodes.toString();
    }
}
