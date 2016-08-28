package com.moesounds.domain.enums;

/**
 * The different types of media a single page could have. 
 */
public enum MediaType implements MappedEnum {

	PAGE_BACKGROUND("PAGE BACKGROUND"),
	BACKGROUND_INNER("BACKGROUND INNER"),
	SOUND_FILE("SOUND FILE"),
	CAROUSEL_IMAGE_SMALL("CAROUSEL IMAGE SMALL"),
	CAROUSEL_IMAGE_BIG("CAROUSEL IMAGE BIG");
	
	private final String name;
	
	/**
	 * Look up a MediaType by it's DB name.
	 * @param code The DB name to look up.
	 * @return The enum constant with matching DB code, or null if there is no such matching enum constant.
	 */
	public static MediaType findByName(String name) {
		for(MediaType corpCode : values())
			if(corpCode.name.equals(name)) return corpCode;
		
		return null;
	}
	
	private MediaType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String getMappedValue() {
		return this.getName();
	}
	
}
