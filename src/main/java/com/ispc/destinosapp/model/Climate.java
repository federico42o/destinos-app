package com.ispc.destinosapp.model;


public enum Climate {
    TROPICAL("Tropical"),
    SUBTROPICAL("Subtropical"),
    MEDITERRANEAN("Mediterranean"),
    TEMPERATE("Temperate"),
    DESERT("Desert"),
    COLD("Cold");

    private final String description;

    Climate(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


