package simpleJson.impl;

import simpleJson.api.JsonDeserializer;
import simpleJson.api.JsonParser;
import simpleJson.api.JsonTypeDeserializer;
import simpleJson.exception.InvalidFieldCountException;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;

public class JsonDeserializerImpl implements JsonDeserializer {
    private final JsonParser jsonParser;

    private Class clazz;

    private Map<Type, JsonTypeDeserializer> fields;

    public JsonDeserializerImpl(JsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    @Override
    public <T> T deserialize(String json, Class<T> clazz) {
        this.clazz = clazz;
        jsonParser.parse(json);
        return null;
    }

    private void validate(){
        int objectFieldCount = getAllFields().length;
        int parsedFieldCount = jsonParser.getObjectCount();
        if (objectFieldCount != parsedFieldCount) throw new InvalidFieldCountException(objectFieldCount, parsedFieldCount);
    }

    private Field[] getAllFields() {
        return clazz.getDeclaredFields();
    }


}
