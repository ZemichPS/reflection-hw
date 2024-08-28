package simpleJson.impl;

import simpleJson.api.JsonParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JsonParserImpl implements JsonParser {
    private Map<String, Object> parsedObjects;
    private String document;
    private List<String> tokens;


    public JsonParserImpl() {
        parsedObjects = new HashMap<>();
    }

    @Override
    public void parse(String json) {
        this.document = json.trim();
    }

    private void tokenize(){

    }

    private void parsingObject() {

    }

    @Override
    public Objects getByName(String objectName) {
        return null;
    }

    @Override
    public int getObjectCount() {
        return parsedObjects.size();
    }
}
