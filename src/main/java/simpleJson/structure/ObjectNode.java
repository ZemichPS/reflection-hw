package simpleJson.structure;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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

    public Optional<Node> getNodeByName(String nodeName) {
        return Optional.ofNullable(children.get(nodeName));
    }

    public Map<String, Node> getChildren() {
        return children;
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
        if(Objects.nonNull(name)) sb.append(name).append(":");
        sb.append(children);
        return sb.toString();
    }
}
