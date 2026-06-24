package com.micronews.identity.dto;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("No user of id " + id + " found", null, false, false);
    }
}
