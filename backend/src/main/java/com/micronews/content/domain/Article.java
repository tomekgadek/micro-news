package com.micronews.content.domain;

import com.micronews.content.dto.ArticleDto;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "article")
class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    String title;
    
    @Column(columnDefinition = "TEXT")
    String content;
    
    @Column(name = "date_art")
    LocalDateTime dateArt;
    
    @Column(name = "id_section")
    Integer idSection;

    Article() {}

    Article(Integer id, String title, String content, LocalDateTime dateArt, Integer idSection) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateArt = dateArt;
        this.idSection = idSection;
    }

    ArticleDto dto() {
        return new ArticleDto(id, title, content, dateArt, idSection);
    }
}
