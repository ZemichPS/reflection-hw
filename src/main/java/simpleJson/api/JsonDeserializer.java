package simpleJson.api;

import java.lang.reflect.InvocationTargetException;

public interface JsonDeserializer {
    <T> T deserialize(String json, Class<T> clazz) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
