package com.brito.enums;

public enum Category {
    BACKEND("BACK-END"), 
    FRONTEND("FRONT-END");

    private String value;

    private Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
