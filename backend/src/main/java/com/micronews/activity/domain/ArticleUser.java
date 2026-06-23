package com.micronews.activity.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "art_user")
@IdClass(ArticleUserId.class)
class ArticleUser {
    @Id
    @Column(name = "id_article")
    private Integer idArticle;
    
    @Id
    @Column(name = "id_user")
    private Integer idUser;
    
    @Id
    @Column(name = "date_read")
    private LocalDateTime dateRead;

    ArticleUser() {}
}
