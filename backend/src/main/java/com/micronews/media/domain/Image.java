package com.micronews.media.domain;

import com.micronews.media.dto.ImageDto;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "image")
class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    String href;
    String title;

    @ElementCollection
    @CollectionTable(name = "art_img", joinColumns = @JoinColumn(name = "id_image"))
    @Column(name = "id_article")
    Set<Integer> articleIds = new HashSet<>();

    Image() {}

    Image(Integer id, String href, String title, Set<Integer> articleIds) {
        this.id = id;
        this.href = href;
        this.title = title;
        this.articleIds = articleIds != null ? new HashSet<>(articleIds) : new HashSet<>();
    }

    ImageDto dto() {
        return new ImageDto(id, href, title, new HashSet<>(articleIds));
    }
}
