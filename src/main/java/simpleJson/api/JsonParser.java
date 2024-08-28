package simpleJson.api;

import java.util.Objects;

public interface JsonParser {

    void parse(String json);

    Objects getByName(String objectName);

    int getObjectCount();
}
