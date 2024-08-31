package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import simpleJson.api.JsonField;

@Data
@NoArgsConstructor
public class Gadget {
    private int id;
    private String name;
    private String description;
}
