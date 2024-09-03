package simpleJson.impl.handlers;

import simpleJson.api.Handler;
import simpleJson.exception.InvalidJsonException;

public class NotEmptyHandler implements Handler {
    @Override
    public void handle(String json, Handler nextHandler) {
        if (!isValid(json)) throw new InvalidJsonException("Json is empty");
        nextHandler.handle(json, nextHandler);
    }

    private boolean isValid(String json) {
        return !json.isEmpty() && !json.isBlank();
    }
}
