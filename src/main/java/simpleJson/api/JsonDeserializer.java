package simpleJson.api;

public interface JsonDeserializer {
     <T> T deserialize(String json, Class<T> clazz);
}
