package com.moesounds.domain.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum DefaultBackground implements MappedEnum {

    GRAY_CHECKERED("Gray Checkered", "images/background-gray-checkered.png", "rgba(73,82,86,.8)"),
    PINK_CHECKERED("Pink Checkered", "images/background-pink-checkered.png", "rgba(224,90,179,.8)");

    private static final Random RANDOM = new Random();
    private static final List<DefaultBackground> VALUES = Collections.unmodifiableList(Arrays.asList(values()));

    private final String name;
    private final String path;
    private final String mainInnerBackgroundCss;

    private DefaultBackground(String name, String path, String mainInnerBackgroundCss) {
        this.name = name;
        this.path = path;
        this.mainInnerBackgroundCss = mainInnerBackgroundCss;
    }

    // Modified Accessors **********************************************************************************************
    public static DefaultBackground randomBackground() {
        return VALUES.get(RANDOM.nextInt(values().length));
    }

    // Default Accessors **********************************************************************************************
    public String getName() {
        return name;
    }
    public String getPath() {
        return path;
    }

    public String getMainInnerBackgroundCss() {
        return mainInnerBackgroundCss;
    }

    @Override
    public String getMappedValue() {
        return this.name;
    }
}
