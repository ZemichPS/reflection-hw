package simpleJson.impl.services;

import simpleJson.api.JsonField;
import simpleJson.api.ReflectionService;
import simpleJson.exception.DeserializeException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class ReflectionServiceImpl implements ReflectionService {

    private Deque<Object> stack = new ArrayDeque<>();

    private Object target;

    @Override
    public void init(Class<?> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
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
                .filter(field -> {
                    if (field.isAnnotationPresent(JsonField.class)) {
                        JsonField annotation = field.getAnnotation(JsonField.class);
                        String jsonFieldName = annotation.fieldName();
                        return fieldName.equals(jsonFieldName);
                    }
                    return field.getName().equals(fieldName);
                })
                .map(Field::getType)
                .findFirst().orElseThrow(() -> new RuntimeException("No such field found: '%s'".formatted(fieldName)));
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
                .filter(field -> {
                    if (field.isAnnotationPresent(JsonField.class)) {
                        JsonField annotation = field.getAnnotation(JsonField.class);
                        String jsonFieldName = annotation.fieldName();
                        return fieldName.equals(jsonFieldName);
                    }
                    return field.getName().equals(fieldName);
                })
                .findFirst().orElseThrow(() -> new RuntimeException("No such field found"));

        targetField.setAccessible(true);
        targetField.set(currentObject, value);
        targetField.setAccessible(false);
    }

    @Override
    public void createInnerObjectByFieldName(String fieldName) throws InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<?> clazz = this.getTargetClassByFieldName(fieldName);
        Object innerObject = null;
        if (clazz.isInterface()) {
            String interfaceName = clazz.getTypeName();
            if (interfaceName.equals("java.util.List")) {
                Class<?> ArraylistClass = Class.forName("java.util.ArrayList");
                innerObject = build(ArraylistClass);
            }
        } else innerObject = build(clazz);

        setValueToField(fieldName, innerObject);
        addToTopStack(innerObject);
    }

    @Override
    public void completeWorkWithNestedElement() {
        stack.pop();
    }

    @Override
    public void addElementToCurrentCollection(Object element) {
        Object currentObject = getCurrentObject();
        if (currentObject instanceof Collection) {
            ((Collection<Object>) currentObject).add(element);
        } else throw new DeserializeException("Current object is not a collection");

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
