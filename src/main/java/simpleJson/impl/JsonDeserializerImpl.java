package simpleJson.impl;

import simpleJson.api.ReflectionService;
import simpleJson.api.JsonDeserializer;
import simpleJson.api.JsonParser;
import simpleJson.api.JsonTypeDeserializer;
import simpleJson.exception.DeserializeException;
import simpleJson.services.ReflectionServiceImpl;
import simpleJson.structure.ArrayNode;
import simpleJson.structure.Node;
import simpleJson.structure.ObjectNode;
import simpleJson.structure.ValueNode;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class JsonDeserializerImpl implements JsonDeserializer {
    private final JsonParser jsonParser;

    private ReflectionService reflectionService = new ReflectionServiceImpl();
    private ObjectNode rootNode;



    private Map<String, JsonTypeDeserializer> jsonTypeDeserializerMap;

    public JsonDeserializerImpl(JsonParser jsonParser, List<JsonTypeDeserializer> typeDeserializers) {
        this.jsonParser = jsonParser;

        this.jsonTypeDeserializerMap = new HashMap<>();
        typeDeserializers.stream().forEach(typeDeserializer -> {
            this.jsonTypeDeserializerMap.put(typeDeserializer.getType(), typeDeserializer);
        });
    }

    @Override
    public <T> T deserialize(String json, Class<T> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        // TODO исправить ошибки
        // validate(json);

        rootNode = jsonParser.parse(json);
        reflectionService.init(clazz);
        doWork();
        Object targetObject = reflectionService.getTargetObject();
        return clazz.cast(targetObject);
    }

    private void doWork() {
        rootNode.getChildren().entrySet()
                .forEach(entry -> {
                    String fieldName = entry.getKey();
                    Node node = entry.getValue();
                    Class<?> fieldClazz = reflectionService.getTargetClassByFieldName(fieldName);
                    Object fieldValue = getValue(node, fieldClazz);
                    try {
                        reflectionService.setValueToField(fieldName, fieldValue);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private Object getValue(Node node, Class<?> clazz) {
        if (node == null) throw new NullPointerException("Node is null");

        if (node instanceof ValueNode valueNode) {
            return handle(valueNode, clazz);
        } else if (node instanceof ObjectNode objectNode) {
            return handle(objectNode);
        } else if (node instanceof ArrayNode arrayNode) {
            return handle(arrayNode);
        }
        throw new DeserializeException("EXCEPTION!!!!!!!");
    }

    private Object handle(ObjectNode objectNode) {
        String className = objectNode.getName();



        objectNode.getChildren().entrySet().stream().forEach(entry -> {});
        return new Object();
    }

    private Object handle(ValueNode valueNode, Class<?> targetFieldType) {
        Object value = valueNode.getValue();

        if (targetFieldType.isPrimitive()) {
            if (valueNode.getValue().getClass().getSimpleName().equals("String")) {
                return mapPrimitive(targetFieldType, String.valueOf(value));
            }
        }
        return jsonTypeDeserializerMap.get(targetFieldType.getTypeName()).parseObject(value);
    }

    private Object handle(ArrayNode arrayNode) {
        return null;
    }


    private void validate(String json) {
        this.jsonParser.validate(json);
    }


    private Object mapPrimitive(Class<?> primitiveType, String value) {
        if (primitiveType == int.class) {
            return Integer.parseInt(value);
        } else if (primitiveType == long.class) {
            return Long.parseLong(value);
        } else if (primitiveType == double.class) {
            return Double.parseDouble(value);
        } else if (primitiveType == boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (primitiveType == float.class) {
            return Float.parseFloat(value);
        } else if (primitiveType == byte.class) {
            return Byte.parseByte(value);
        } else if (primitiveType == short.class) {
            return Short.parseShort(value);
        } else if (primitiveType == char.class) {
            return value.charAt(0); // Если это строка длиной 1, вы можете преобразовать ее в символ
        } else {
            throw new IllegalArgumentException("Unsupported primitive type: " + primitiveType);
        }
    }
}

