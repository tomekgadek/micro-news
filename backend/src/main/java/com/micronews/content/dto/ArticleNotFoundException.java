package com.micronews.content.dto;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(Integer id) {
        super("No article of id " + id + " found", null, false, false);
    }
}
