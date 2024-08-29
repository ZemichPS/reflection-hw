package simpleJson.impl.typedes;

import simpleJson.exception.ParseException;

import java.util.*;

public class Tokenizer {
    private String json;
    private int cursor = 0;
    private String currentKey = "";
    private final Map<String, Object> workMap = new TreeMap<>();
    private String jsonObjectName = "";
    int levelDeep = 0;


    public void tokenize(String input) {
        json = input.substring(1, input.lastIndexOf("}"))
                .trim().replaceAll("\\s{2,}", "");
        doWork();
        System.out.println("Мапа: " + workMap);
    }

    private boolean hasNext() {
        return cursor < json.length() - 1;
    }

    private void doWork() {
        int currentNodeLevel = 0;
        while (hasNext()) {
            switch (json.charAt(cursor)) {
                case '"' -> handleMemberName();
                case '{' -> handleJsonObject();
                case '[' -> handleList();
                case ':' -> checkAndTryToHandlePrimitive();
                default -> cursor++;
            }
        }
    }

    private void handleJsonObject() {
        cursor++;
        if (!currentKey.isEmpty()) {
            jsonObjectName = currentKey;
            currentKey = "";
        }
        doWork();
    }

    void checkAndTryToHandlePrimitive() {
        String forbiddenChars = "{}[],";
        char nextChar = json.charAt(cursor + 1);

        // TODO КОСТЫЛЬ
        if (nextChar == ' ') nextChar = json.charAt(cursor + 1);
        if (nextChar == ' ') nextChar = json.charAt(cursor + 2);
        if (nextChar == ' ') nextChar = json.charAt(cursor + 3);

        if (forbiddenChars.indexOf(nextChar) != -1) {
            cursor++;
            return;
        }

        Object value = handlePrimitive();
        if (currentKey.isEmpty()) throw new

                ParseException("The parser encountered an unexpected scenario");
        workMap.put(currentKey, value);
        currentKey = "";
    }

    private Object handlePrimitive() {
        String value = json.substring(cursor, json.indexOf(",", cursor));
        cursor = cursor + value.length() + 1;
        value = value.replaceAll(":", "").trim();
        if (value.contains("\"")) value = value.replaceAll("\"", "");
        return value;
    }

    private void handleMemberName() {
        if (!currentKey.isEmpty()) return;
        int subIndex = json.indexOf('"', cursor + 1);
        currentKey = json.substring(cursor + 1, subIndex);
        cursor = subIndex + 1;
    }

    private Object handleDigits(int startIndex) {
        return null;
    }

    private void handleList() {
        int END_ARRAY_INDEX = json.indexOf(']', cursor + 1);
        String listSubString = json.substring(cursor + 1, END_ARRAY_INDEX);

        if (!listSubString.contains("{")) {
            String[] primitives = listSubString.replaceAll("\"", "").split(",");
            workMap.put(currentKey, primitives);
            currentKey = "";
            cursor = END_ARRAY_INDEX + 1;
            return;
        } else {
            doWork();
        }
        char END_ARRAY_CHAR = json.charAt(END_ARRAY_INDEX);
        cursor++;
    }

    public Map<String, Object> getWorkMap() {
        return workMap;
    }
}
