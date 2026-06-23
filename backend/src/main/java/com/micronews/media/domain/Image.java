package com.micronews.media.domain;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "image")
class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String href;
    private String title;

    @ElementCollection
    @CollectionTable(name = "art_img", joinColumns = @JoinColumn(name = "id_image"))
    @Column(name = "id_article")
    private Set<Integer> articleIds = new HashSet<>();

    Image() {}
}
