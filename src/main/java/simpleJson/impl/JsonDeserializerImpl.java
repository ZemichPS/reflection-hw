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
    private final ReflectionService reflectionService = new ReflectionServiceImpl();
    private ObjectNode rootNode;
    private final Map<String, JsonTypeDeserializer> jsonTypeDeserializerMap;

    public JsonDeserializerImpl(JsonParser jsonParser, List<JsonTypeDeserializer> typeDeserializers) {
        this.jsonParser = jsonParser;

        this.jsonTypeDeserializerMap = new HashMap<>();
        typeDeserializers.forEach(typeDeserializer -> this.jsonTypeDeserializerMap.put(typeDeserializer.getType(), typeDeserializer));
    }

    @Override
    public <T> T deserialize(String json, Class<T> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        rootNode = jsonParser.parse(json);
        reflectionService.init(clazz);
        initDeserialize();
        Object targetObject = reflectionService.getTargetObject();
        return clazz.cast(targetObject);
    }

    @Override
    public void addJsonTypeDeserializer(JsonTypeDeserializer deserializer) {
        jsonTypeDeserializerMap.put(deserializer.getType(), deserializer);
    }

    private void initDeserialize() {
        rootNode.getChildren().forEach((fieldName, node) -> {
            try {
                handleNodeByNodeType(fieldName, node);
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException |
                     ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void handleNodeByNodeType(String fieldName, Node node) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Objects.requireNonNull(node, "Node is null");

        if (node instanceof ValueNode valueNode) {
            handle(valueNode, fieldName);
        } else if (node instanceof ObjectNode objectNode) {
            handle(objectNode);
        } else if (node instanceof ArrayNode arrayNode) {
            handle(arrayNode);
        } else throw new DeserializeException("Node is not recognized");
    }

    private void handle(ObjectNode objectNode) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        String fieldName = objectNode.getName();
        reflectionService.createInnerObjectByFieldName(fieldName);
        objectNode.getChildren().forEach((fName, node) -> {
            try {
                handleNodeByNodeType(fName, node);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        reflectionService.completeWorkWithNestedElement();
    }

    private void handle(ValueNode valueNode, String fieldName) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> fieldClazz = reflectionService.getTargetClassByFieldName(fieldName);
        Object value = valueNode.getValue();

        if (fieldClazz.isPrimitive()) {
            if (valueNode.getValue().getClass().getSimpleName().equals("String")) {
                value = mapPrimitive(fieldClazz, String.valueOf(value));
            }
        } else value = jsonTypeDeserializerMap.get(fieldClazz.getTypeName()).parseObject(value);
        reflectionService.setValueToField(fieldName, value);
    }

    private void handle(ArrayNode arrayNode) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        String listFieldName = arrayNode.getName();
        List<Node> valueList = arrayNode.getChildNodes();
        if(valueList.isEmpty()) return;
        reflectionService.createInnerObjectByFieldName(listFieldName);
        valueList.forEach(reflectionService::addElementToCurrentCollection);
        reflectionService.completeWorkWithNestedElement();
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

