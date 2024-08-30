package model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Person {
    private UUID id;
    private String name;
    private int age;
    private Address address;
    List<Permission> permissions;
}


