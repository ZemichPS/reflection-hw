package simpleJson.impl.typedes;

import simpleJson.api.JsonTypeDeserializer;

public class IntegerDeserializer implements JsonTypeDeserializer {
    @Override
    public Integer parseObject(Object obj) throws IllegalArgumentException {
        if (obj instanceof Integer integer){
            return integer;
        }
        throw new IllegalArgumentException("Type error. Actual type: " + obj.getClass());
    }

    @Override
    public String getType() {
        return "java.lang.Integer";
    }
}
