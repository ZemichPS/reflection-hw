package simpleJson.services;

import simpleJson.api.ReflectionService;
import simpleJson.exception.DeserializeException;
import simpleJson.structure.Node;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;


public class ReflectionServiceImpl implements ReflectionService {

    private Deque<Object> stack = new ArrayDeque<>();

    private Object target;
    private Class<?> clazz;

    @Override
    public void init(Class<?> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        this.clazz = clazz;
        target = build(clazz);
        stack.clear();
        stack.push(target);
    }

    @Override
    public Object getTargetObject() {
        return target;
    }

    @Override
    public Class<?> getTargetClassByFieldName(String fieldName) {
        return Arrays.stream(getCurrentObject().getClass().getDeclaredFields())
                .filter(field -> field.getName().equals(fieldName))
                .map(Field::getType)
                .findFirst().orElseThrow(() -> new RuntimeException("No such field found"));
    }

    private Object build(Class<?> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> defaultConstructor = Arrays.stream(clazz.getDeclaredConstructors())
                .filter(constructor -> constructor.getParameterCount() == 0)
                .findFirst().orElseThrow(() -> new RuntimeException("No default constructor found"));
        return defaultConstructor.newInstance();
    }

    public void setValueToField(String fieldName, Object value) throws IllegalAccessException {
        Object currentObject = getCurrentObject();
        Objects.requireNonNull(currentObject);

        Field targetField = Arrays.stream(currentObject.getClass().getDeclaredFields())
                .filter(field -> field.getName().equals(fieldName))
                .findFirst().orElseThrow(() -> new RuntimeException("No such field found"));

        targetField.setAccessible(true);
        targetField.set(currentObject, value);
        targetField.setAccessible(false);
    }

    @Override
    public void createInnerObjectByFieldName(String fieldName) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = this.getTargetClassByFieldName(fieldName);
        Object innerObject = build(clazz);
        setValueToField(fieldName, innerObject);
        addToTopStack(innerObject);
    }

    @Override
    public void completeWorkWithNestedElement() {
        stack.pop();
    }
    private Object getCurrentObject() {
        return stack.peek();
    }

    private void addToTopStack(Object object) {
        Objects.requireNonNull(stack, "Stack is null");
        Objects.requireNonNull(object, "Object is null");
        stack.push(object);
    }
}
