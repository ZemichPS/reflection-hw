package simpleJson.impl;

import simpleJson.api.JsonDeserializer;
import simpleJson.api.JsonParser;
import simpleJson.api.JsonTypeDeserializer;
import simpleJson.exception.InvalidFieldCountException;
import simpleJson.structure.ObjectNode;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JsonDeserializerImpl implements JsonDeserializer {
    private final JsonParser jsonParser;

    private Class clazz;
    private String json;

    private Map<String, JsonTypeDeserializer> jsonTypeDeserializerMap;

    public JsonDeserializerImpl(JsonParser jsonParser, List<JsonTypeDeserializer> typeDeserializers) {
        this.jsonParser = jsonParser;
        typeDeserializers.stream().forEach(typeDeserializer -> {
            this.jsonTypeDeserializerMap.put(typeDeserializer.getType(), typeDeserializer);
        });
    }

    @Override
    public <T> T deserialize(String json, Class<T> clazz) {
        this.clazz = clazz;
        this.json = json;
        validate();
        ObjectNode objectNode = jsonParser.parse(json);
        return null;
    }

    private void validate() {
        this.jsonParser.validate(json);
    }

    private Field[] getAllFields() {
        return clazz.getDeclaredFields();
    }


}
