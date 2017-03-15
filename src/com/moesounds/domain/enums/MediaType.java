package com.moesounds.domain.enums;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

/**
 * The different types of media a single page could have.
 */
public enum MediaType implements MappedEnum {

    PAGE_BACKGROUND("PAGE BACKGROUND", "Background image of the entire page. Wide-screen format is recommended", false),
    BACKGROUND_INNER("BACKGROUND INNER", "Background image of the center box. Roughly 700x470 pixels", true),
    CAROUSEL_IMAGE("CAROUSEL IMAGE", "Roughly 200x200 pixels", true),
    THUMBNAIL_ICON("THUMBNAIL ICON", "Roughly 50x50 pixels", true),
    SOUND_FILE("SOUND FILE", "Really short sound file. Less than 3 seconds", true);

    private final String name;
    private final String fileSuggestion;
    private final boolean required;

    private MediaType(String name, String fileSuggestion, boolean required) {
        this.name = name;
        this.fileSuggestion = fileSuggestion;
        this.required = required;
    }

    public static final List<MediaType> IMAGE_TYPES = Arrays.asList(PAGE_BACKGROUND, BACKGROUND_INNER, CAROUSEL_IMAGE, THUMBNAIL_ICON);
    public static final List<MediaType> SOUND_TYPES = Arrays.asList(SOUND_FILE);

    // Modified Accessors *********************************************************************************************

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

    public String getNameWithoutAllCaps() {
        return WordUtils.capitalizeFully(this.name, new char[] {' '});
    }

    // Default Accessors **********************************************************************************************
    public String getName() {
        return name;
    }
    public String getFileSuggestion() {
        return fileSuggestion;
    }

    public boolean isRequired() {
        return required;
    }


    @Override
    public String getMappedValue() {
        return this.getName();
    }

}
