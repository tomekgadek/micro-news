package com.micronews.activity.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

class ArticleUserId implements Serializable {
    private Integer idArticle;
    private Integer idUser;
    private LocalDateTime dateRead;

    ArticleUserId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleUserId that = (ArticleUserId) o;
        return Objects.equals(idArticle, that.idArticle) &&
               Objects.equals(idUser, that.idUser) &&
               Objects.equals(dateRead, that.dateRead);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArticle, idUser, dateRead);
    }
}
