package model;

import lombok.Data;

import java.util.UUID;

@Data
public class Product {
    private UUID uuid;
    private String name;
    private String description;
    private Double price;
    private boolean sale;
}
