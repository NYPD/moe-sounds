package com.moesounds.exception;

import java.io.IOException;

public class FileReadException extends RuntimeException {

    public FileReadException(IOException e) {
        super(e);
    }
}
