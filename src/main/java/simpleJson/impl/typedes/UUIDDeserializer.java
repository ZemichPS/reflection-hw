package simpleJson.impl.typedes;

import simpleJson.api.JsonTypeDeserializer;

import java.util.UUID;

public class UUIDDeserializer implements JsonTypeDeserializer {
    @Override
    public UUID parseObject(Object obj) throws IllegalArgumentException {
        if (obj instanceof String stringUuid){
            return UUID.fromString(stringUuid);
        }
        throw new IllegalArgumentException("Type error. Actual type: " + obj.getClass());
    }

    @Override
    public String getType() {
        return "java.util.UUID";
    }
}
