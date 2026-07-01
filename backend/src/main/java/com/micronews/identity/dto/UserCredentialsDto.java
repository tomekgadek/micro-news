package com.micronews.identity.dto;

public record UserCredentialsDto(String login, String passwordHash, String role) {}
