package simpleJson.api;

import simpleJson.structure.ObjectNode;

import java.util.Objects;

public interface JsonParser {
    ObjectNode parse(String json);

    void validate(String json);
}
