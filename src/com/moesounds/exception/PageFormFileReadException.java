package com.moesounds.exception;

import java.io.IOException;

public class PageFormFileReadException extends RuntimeException {

	public PageFormFileReadException(IOException e) {
		super(e);
	}
}
