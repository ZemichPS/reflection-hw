package simpleJson.exception;

public abstract class JsonDeserializerException extends RuntimeException{
    public JsonDeserializerException(String message) {
        super(message);
    }
}
