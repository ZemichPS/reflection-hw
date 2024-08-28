package simpleJson.impl.typedes;

import simpleJson.api.JsonTypeDeserializer;

public class StringDeserializer implements JsonTypeDeserializer {
    @Override
    public String getObject(Object obj) throws IllegalArgumentException {
        try {
            return (String) obj;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String getType() {
        return "String";
    }
}
