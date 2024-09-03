package simpleJson.impl.handlers;

import simpleJson.api.Handler;
import simpleJson.exception.InvalidJsonException;

import java.util.Objects;

public class NotNullHandler implements Handler {
    @Override
    public void handle(String json, Handler nextHandler) {
        if (isValid(json)) throw new InvalidJsonException("Source is null");
        nextHandler.handle(json, nextHandler);
    }

    private boolean isValid(String json) {
        return Objects.isNull(json);
    }
}
