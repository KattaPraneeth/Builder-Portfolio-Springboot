package com.praneeth.Exceptions;

public class BuilderNotFoundException extends RuntimeException {
    public BuilderNotFoundException(String message) {
        super(message);
    }
}