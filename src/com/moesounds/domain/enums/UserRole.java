package com.moesounds.domain.enums;


public enum UserRole implements MappedEnum {

    ADMIN("ADMIN");

    private final String name;

    private UserRole(String name) {
        this.name = name;
    }

    @Override
    public String getMappedValue() {
        return this.name;
    }

}

