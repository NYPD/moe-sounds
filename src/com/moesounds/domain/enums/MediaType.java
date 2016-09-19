package com.moesounds.domain.enums;

import java.util.Arrays;
import java.util.List;

/**
 * The different types of media a single page could have.
 */
public enum MediaType implements MappedEnum {

    PAGE_BACKGROUND("PAGE BACKGROUND"), BACKGROUND_INNER("BACKGROUND INNER"), SOUND_FILE(
            "SOUND FILE"), CAROUSEL_IMAGE_SMALL("CAROUSEL IMAGE SMALL"), CAROUSEL_IMAGE_BIG("CAROUSEL IMAGE BIG");

    private final String name;

    private MediaType(String name) {
        this.name = name;
    }

    public static final List<MediaType> IMAGE_TYPES = Arrays.asList(PAGE_BACKGROUND, BACKGROUND_INNER, CAROUSEL_IMAGE_SMALL, CAROUSEL_IMAGE_BIG);
    public static final List<MediaType> SOUND_TYPES = Arrays.asList(SOUND_FILE);

    // Modified Accessors ******************

    /**
     * Look up a MediaType by it's DB name.
     * 
     * @param code
     *            The DB name to look up.
     * @return The enum constant with matching DB code, or null if there is no such matching enum
     *         constant.
     */
    public static MediaType findByName(String name) {
        for (MediaType corpCode : values())
            if (corpCode.name.equals(name)) return corpCode;

        return null;
    }

    public boolean isImage() {
        MediaType mediaType = findByName(this.name);
        return IMAGE_TYPES.indexOf(mediaType) != -1;
    }

    public boolean isSound() {
        MediaType mediaType = findByName(this.name);
        return SOUND_TYPES.indexOf(mediaType) != -1;
    }

    // Default Accessors *******************
    public String getName() {

        return name;
    }

    @Override
    public String getMappedValue() {

        return this.getName();
    }

}
