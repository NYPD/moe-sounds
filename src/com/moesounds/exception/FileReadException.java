package com.moesounds.exception;

import java.io.IOException;

/**
 * Generic runtime exception meant to encapsulate all {@link IOException}s that occurred when trying
 * to read a File
 * 
 * @author NYPD
 *
 */
public class FileReadException extends RuntimeException {

    private static final long serialVersionUID = -6126425140516722051L;

    public FileReadException(IOException e) {
        super(e);
    }
}
