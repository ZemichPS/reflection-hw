package simpleJson.api;

public interface JsonTypeDeserializer{
    <T> T parseObject(Object obj) throws IllegalArgumentException ;
    String getType();
}
