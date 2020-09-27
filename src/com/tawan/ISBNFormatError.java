package com.tawan;

public class ISBNFormatError extends Exception {

    public ISBNFormatError(String message, Throwable err) {
        super(message, err);
    }
}
