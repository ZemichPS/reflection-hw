package simpleJson.impl.typedes;

import simpleJson.api.JsonTypeDeserializer;

public class DoubleDeserializer implements JsonTypeDeserializer {
    @Override
    public Double getObject(Object obj) throws IllegalArgumentException {
        if (obj instanceof Double d){
            return d;
        }
        throw new IllegalArgumentException("Type error. Actual type: " + obj.getClass());
    }

    @Override
    public String getType() {
        return "Double";
    }
}
