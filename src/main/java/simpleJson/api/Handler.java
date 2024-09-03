package simpleJson.api;

public interface Handler {
    void handle(String json, Handler nextHandler);
}
