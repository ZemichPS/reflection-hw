package simpleJson.structure;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ObjectNode extends Node {

    private Map<String, Node> children = new HashMap<>();
    private String name;

    public ObjectNode(String name) {
        this.name = name;
    }

    public ObjectNode() {
    }

    public void addNode(String nodeName, Node node) {
        children.put(nodeName, node);
    }

    public Node getNode(String nodeName) {
        return children.get(nodeName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(Objects.nonNull(name)) sb.append(": ").append(name);
        sb.append(":").append(children);
        return sb.toString();
    }
}
