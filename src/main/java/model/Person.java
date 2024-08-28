package model;

import lombok.*;

@Data
public class Person {
    private String name;
    private int age;
    private boolean admin;

    @Data
    public class Address {
        private String country;
        private String region;
        private String city;
        private String street;
        private int buildingNumber;
        private String zip;

    }
}


