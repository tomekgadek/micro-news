package com.micronews.content.dto;

public class SectionNotFoundException extends RuntimeException {
    public SectionNotFoundException(Integer id) {
        super("No section of id " + id + " found", null, false, false);
    }
}
