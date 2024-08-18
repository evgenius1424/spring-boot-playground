package com.github.evgenius1424.spring_boot_playground;

public class CreateItemRequest {
    private String name;
    private String brand;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}