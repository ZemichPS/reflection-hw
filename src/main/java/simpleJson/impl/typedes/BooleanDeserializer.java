package simpleJson.impl.typedes;

import simpleJson.api.JsonTypeDeserializer;

public class BooleanDeserializer implements JsonTypeDeserializer {

    @Override
    public Boolean parseObject(Object obj) throws IllegalArgumentException {
        if (obj instanceof Boolean bool){
            return bool;
        } else if (obj instanceof String) {
            return Boolean.parseBoolean((String) obj);
        }
        throw new IllegalArgumentException("Type error. Actual type: " + obj.getClass());
    }

    @Override
    public String getType() {
        return "boolean";
    }
}
