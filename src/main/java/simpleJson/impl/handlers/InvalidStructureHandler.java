package simpleJson.impl.handlers;

import simpleJson.api.Handler;
import simpleJson.exception.InvalidJsonException;
import simpleJson.exception.ParseException;

import java.util.Objects;

public class InvalidStructureHandler implements Handler {
    @Override
    public void handle(String json, Handler nextHandler) {
        if (!isValid(json)) throw new InvalidJsonException("Invalid JSON structure empty");
        if (Objects.nonNull(nextHandler)) {
            nextHandler.handle(json, nextHandler);
        }

    }

    private boolean isValid(String json) {
        int notStaceSymbol = 0;
        while (Character.isWhitespace(json.charAt(notStaceSymbol))) {
            notStaceSymbol++;
        }
        char startChar = json.charAt(notStaceSymbol);

        notStaceSymbol = json.length() - 1;
        while (Character.isWhitespace(json.charAt(notStaceSymbol))) {
            notStaceSymbol--;
        }

        char endChar = json.charAt(notStaceSymbol);
        return startChar == '{' && endChar == '}';
    }
}
