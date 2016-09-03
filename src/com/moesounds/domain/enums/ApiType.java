package com.moesounds.domain.enums;


public enum ApiType implements MappedEnum {

    GOOGLE("GOOGLE");

    private final String name;

    private ApiType(String name) {
        this.name = name;
    }

    @Override
    public String getMappedValue() {
        return this.name;
    }

}
