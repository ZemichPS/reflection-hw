package simpleJson.impl.handlers;

import simpleJson.api.Handler;

public class NullObjectHandler implements Handler {
    @Override
    public void handle(String json, Handler nextHandler) {

    }

    private boolean isValid(String json) {
      return true;
    }
}
