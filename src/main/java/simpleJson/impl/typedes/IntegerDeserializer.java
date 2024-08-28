package simpleJson.impl.typedes;

import simpleJson.api.JsonTypeDeserializer;

public class IntegerDeserializer implements JsonTypeDeserializer {
    @Override
    public Integer getObject(Object obj) throws IllegalArgumentException {
        try {
            return (Integer) obj;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String getType() {
        return "String";
    }
}
