package com.example.smartcitysystem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class SensorRequest {

    @NotBlank
    private String type;

    @Min(value = 0, message = "value 0'dan küçük olamaz")
    private int value;

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }


    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}
