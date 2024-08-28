package simpleJson.api;

public interface JsonTypeDeserializer{
    <T> T getObject(Object obj) throws IllegalArgumentException ;
    String getType();
}
