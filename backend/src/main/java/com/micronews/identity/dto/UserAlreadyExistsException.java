package com.micronews.identity.dto;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String login) {
        super("User with login " + login + " already exists", null, false, false);
    }
}
