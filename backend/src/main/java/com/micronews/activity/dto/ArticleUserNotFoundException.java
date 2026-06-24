package com.micronews.activity.dto;

public class ArticleUserNotFoundException extends RuntimeException {
    public ArticleUserNotFoundException(Integer idArticle, Integer idUser) {
        super("No read history found for article " + idArticle + " and user " + idUser, null, false, false);
    }
}
