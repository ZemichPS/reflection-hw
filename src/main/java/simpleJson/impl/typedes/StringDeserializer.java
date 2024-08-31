package simpleJson.impl.typedes;

import simpleJson.api.JsonTypeDeserializer;

public class StringDeserializer implements JsonTypeDeserializer {
    @Override
    public String parseObject(Object obj) throws IllegalArgumentException {
        if (obj instanceof String s){
            return s;
        }
        throw new IllegalArgumentException("Type error. Actual type: " + obj.getClass());
    }

    @Override
    public String getType() {
        return "java.lang.String";
    }
}
