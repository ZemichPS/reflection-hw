package simpleJson.impl.typedes;

import simpleJson.api.JsonTypeDeserializer;

public class DoubleDeserializer implements JsonTypeDeserializer {
    @Override
    public Double parseObject(Object obj) throws IllegalArgumentException {
        if (obj instanceof Double d){
            return d;
        } else if (obj instanceof String s){
            return Double.parseDouble(s);
        }
        throw new IllegalArgumentException("Type error. Actual type: " + obj.getClass());
    }

    @Override
    public String getType() {
        return "java.lang.Double";
    }
}
