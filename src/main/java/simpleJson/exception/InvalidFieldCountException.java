package simpleJson.exception;

public class InvalidFieldCountException extends JsonParserException {

    public InvalidFieldCountException(int fieldCount, int expectedFieldCount) {
        super("Target object has %d, but parsed %d".formatted(expectedFieldCount, fieldCount));
    }
}

