package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import simpleJson.api.JsonField;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductDto {
    private UUID uuid;
    private String name;
    private String description;
    @JsonField(fieldName = "price$")
    private Double price;
    private boolean sale;
    private Supplier supplier;
    @JsonField(fieldName = "inBox")
    private List<Gadget> inBoxGadget;
    private List<String> availableFunctions;
}
