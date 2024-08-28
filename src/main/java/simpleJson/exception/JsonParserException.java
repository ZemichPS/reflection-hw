package simpleJson.exception;

public abstract class JsonParserException extends RuntimeException {
    public JsonParserException(String message) {
        super(message);
    }
}
