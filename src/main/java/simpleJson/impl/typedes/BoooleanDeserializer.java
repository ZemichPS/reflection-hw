package simpleJson.impl.typedes;

import simpleJson.api.JsonTypeDeserializer;

public class BoooleanDeserializer implements JsonTypeDeserializer {

    @Override
    public Boolean getObject(Object obj) throws IllegalArgumentException {
        if (obj instanceof Boolean bool){
            return bool;
        }
        throw new IllegalArgumentException("Type error. Actual type: " + obj.getClass());
    }

    @Override
    public String getType() {
        return "Boolean";
    }
}
