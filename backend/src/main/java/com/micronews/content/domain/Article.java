package com.micronews.content.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "article")
class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String content;
    
    @Column(name = "date_art")
    private LocalDateTime dateArt;
    
    @Column(name = "id_section")
    private Integer idSection;

    Article() {}
}
