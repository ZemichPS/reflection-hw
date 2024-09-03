package simpleJson.exception;

public class InvalidJsonException extends JsonParserException{
    public InvalidJsonException(String message) {
        super(message);
    }
}
