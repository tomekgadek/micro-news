package com.micronews.content;

import com.micronews.content.domain.ContentFacade;
import com.micronews.content.dto.PublicArticleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class ContentController {
    private final ContentFacade contentFacade;

    public ContentController(ContentFacade contentFacade) {
        this.contentFacade = contentFacade;
    }

    @GetMapping("/articles/public")
    public ResponseEntity<List<PublicArticleDto>> getRecentPublicArticles() {
        return ResponseEntity.ok(contentFacade.getRecentPublicArticles());
    }
}
