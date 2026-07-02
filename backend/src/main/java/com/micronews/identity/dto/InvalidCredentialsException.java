package com.micronews.identity.dto;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Invalid login or password", null, false, false);
    }
}
