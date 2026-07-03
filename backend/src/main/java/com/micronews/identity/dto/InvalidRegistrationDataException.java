package com.micronews.identity.dto;

public class InvalidRegistrationDataException extends RuntimeException {
    public InvalidRegistrationDataException(String message) {
        super(message, null, false, false);
    }
}
