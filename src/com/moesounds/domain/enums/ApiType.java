package com.moesounds.domain.enums;

import java.lang.annotation.Annotation;

import com.moesounds.annotation.GoogleLogin;

public enum ApiType implements MappedEnum {

    GOOGLE("GOOGLE", GoogleLogin.class);

    private final String name;
    private final Class<? extends Annotation> apiLoginServiceClass;

    private ApiType(String name, Class<? extends Annotation> clazz) {
        this.name = name;
        this.apiLoginServiceClass = clazz;
    }

    /**
     * Finds the ApiType by name without throwing an exception like enum.valueOf(). If no ApiType is
     * found, return null.
     * 
     * @param name - The name to find by
     * @return ApiType
     */
    public static ApiType findByName(String name) {

        for (ApiType apiType : values())
            if (apiType.name.equals(name)) return apiType;

        return null;
    }

    @Override
    public String getMappedValue() {
        return this.name;
    }

    public Class<? extends Annotation> getApiLoginServiceClass() {
        return apiLoginServiceClass;
    }

}
