package simpleJson.api;

import java.lang.reflect.InvocationTargetException;

public interface ReflectionService {
    void init(Class<?> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException;

    Object getTargetObject();

    Class<?> getTargetClassByFieldName(String fieldName);

    void setValueToField(String fieldName, Object value) throws IllegalAccessException;

    void createInnerObjectByFieldName(String fieldName) throws InvocationTargetException, InstantiationException, IllegalAccessException;
    void completeWorkWithNestedElement();

}
