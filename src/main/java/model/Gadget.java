package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Gadget {
    private int id;
    private String name;
    private String description;
}
