package com.micronews.media.dto;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(Integer id) {
        super("No image of id " + id + " found", null, false, false);
    }
}
