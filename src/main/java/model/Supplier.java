package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Supplier {
    private long id;
    private String name;
    private String description;
}
