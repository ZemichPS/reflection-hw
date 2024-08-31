package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductDto {
    private UUID uuid;
    private String name;
    private String description;
    private Double price;
    private boolean sale;
    private Supplier supplier;
}
