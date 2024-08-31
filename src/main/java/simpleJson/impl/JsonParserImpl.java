package simpleJson.impl;

import simpleJson.api.JsonParser;
import simpleJson.exception.ParseException;
import simpleJson.structure.ArrayNode;
import simpleJson.structure.Node;
import simpleJson.structure.ObjectNode;
import simpleJson.structure.ValueNode;

import java.util.*;

public class JsonParserImpl implements JsonParser {
    private String json;
    private int cursor = 0;
    private String currentKey = "";

    private Deque<Node> stack = new ArrayDeque<>();
    private ObjectNode rootNode = null;

    private String EMPTY_TOKEN_KEY_ERROR_MESSAGE = "The parser encountered an unexpected scenario. Key is empty";
    private String STACK_IS_EMPTY_ERROR_MESSAGE = "The parser encountered an unexpected scenario. Stack is empty";


    public ObjectNode parse(String input) {
        rootNode = new ObjectNode("root");
        stack.push(rootNode);
        json = input.substring(1, input.length() - 1);
        json = json.trim()
                .replaceAll("\\s{2,}", "");
        doWork();
        return rootNode;
    }

    @Override
    public void validate(String json) {
        if (json == null
                || json.isBlank()
                || !json.startsWith("{")
                || !json.endsWith("}")
        ) {
            throw new ParseException("JSON source invalid");
        }
    }

    private boolean hasNext() {
        return cursor < json.length() - 1;
    }

    private void doWork() {
        while (hasNext()) {
            switch (json.charAt(cursor)) {
                case '"' -> handleMemberName();
                case '{' -> handleStartJsonObject();
                case '}' -> handleEndJsonObject();
                case '[' -> handleStartList();
                case ']' -> handleEndList();
                case ':' -> checkAndTryToHandlePrimitive();
                default -> cursor++;
            }
        }
    }

    private void handleStartJsonObject() {
        cursor++;
        ObjectNode newobjectNode = new ObjectNode();
        if (!currentKey.isEmpty()) newobjectNode.setName(currentKey);
        addToCurrentObjectNote(newobjectNode);
        stack.push(newobjectNode);
        currentKey = "";
        doWork();
    }

    private void handleEndJsonObject() {
        if (stack.isEmpty()) throw new ParseException(STACK_IS_EMPTY_ERROR_MESSAGE);
        stack.pop();
        cursor++;
    }

    private void handleEndList() {
        if (stack.isEmpty()) throw new ParseException(STACK_IS_EMPTY_ERROR_MESSAGE);
        cursor++;
        stack.pop();
    }

    void checkAndTryToHandlePrimitive() {
        String forbiddenChars = "{}[],";
        cursor++;

        while (Character.isWhitespace(json.charAt(cursor))) {
            cursor++;
        }
        char nextChar = json.charAt(cursor);

        if (forbiddenChars.indexOf(nextChar) != -1) {
            return;
        }

        if (currentKey.isEmpty()) throw new ParseException(EMPTY_TOKEN_KEY_ERROR_MESSAGE);
        Object value = handlePrimitive();
        addToCurrentObjectNote(new ValueNode(value));
        currentKey = "";
    }

    private Object handlePrimitive() {
        String value = "";
        char firstValueChar = json.charAt(cursor);

        if (firstValueChar == '"') {
            value = json.substring(cursor + 1, json.indexOf('"', cursor + 1));
            cursor = cursor + 2;
        } else if (firstValueChar == '-' || Character.isDigit(firstValueChar)) {
            int valueEndIndex = cursor + 1;

            while (Character.isDigit(json.charAt(valueEndIndex)) || json.charAt(valueEndIndex) == '.' ||
                    json.charAt(valueEndIndex) == 'e' || json.charAt(valueEndIndex) == 'E' ||
                    json.charAt(valueEndIndex) == '-' || json.charAt(valueEndIndex) == '+') {
                valueEndIndex++;
            }
            value = json.substring(cursor, valueEndIndex);
        } else if (json.startsWith("true", cursor)) {
            value = "true";
        } else if (json.startsWith("false", cursor)) {
            value = "false";
        } else if (json.startsWith("null", cursor)) {
            value = "null";
        }
        cursor = cursor + value.length();
        return value;
    }

    private void handleMemberName() {
        if (!currentKey.isEmpty()) return;
        int subIndex = json.indexOf('"', cursor + 1);
        currentKey = json.substring(cursor + 1, subIndex);
        cursor = subIndex + 1;
    }

    private void handleStartList() {
        if (currentKey.isEmpty()) throw new ParseException(EMPTY_TOKEN_KEY_ERROR_MESSAGE);

        int END_ARRAY_INDEX = json.indexOf(']', cursor + 1);
        String listSubString = json.substring(cursor + 1, END_ARRAY_INDEX);

        if (!listSubString.contains("{")) {
            ArrayNode arrayNode = new ArrayNode(currentKey);
            Arrays.stream(listSubString.replaceAll("\"", "")
                            .split(","))
                    .map(ValueNode::new)
                    .forEachOrdered(arrayNode::addNode);

            addToCurrentObjectNote(arrayNode);
            currentKey = "";
            cursor = END_ARRAY_INDEX + 1;
            return;
        } else {
            ArrayNode arrayNode = new ArrayNode(currentKey);
            addToCurrentObjectNote(arrayNode);
            stack.push(arrayNode);
            currentKey = "";
            cursor++;
            doWork();
        }
        cursor++;

    }

    private void addToCurrentObjectNote(Node node) {
        if (stack.peek() instanceof ObjectNode top) {
            top.addNode(currentKey, node);
        } else if (stack.peek() instanceof ArrayNode top) {
            top.addNode(node);
        } else throw new ParseException("The parser encountered an unexpected scenario");
    }


}
