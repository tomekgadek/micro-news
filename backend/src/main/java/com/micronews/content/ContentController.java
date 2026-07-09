package com.micronews.content;

import com.micronews.content.domain.ContentFacade;
import com.micronews.content.dto.ArticleDetailsDto;
import com.micronews.content.dto.ArticleSummaryDto;
import com.micronews.content.dto.PublicArticleDto;
import com.micronews.content.dto.SectionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/articles")
    public ResponseEntity<Page<ArticleSummaryDto>> getArticles(
            @PageableDefault(size = 10, sort = "dateArt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(contentFacade.getArticles(pageable));
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleDetailsDto> getArticleDetails(@PathVariable Integer id) {
        return ResponseEntity.ok(contentFacade.getArticleDetails(id));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<SectionDto>> getCategories() {
        return ResponseEntity.ok(contentFacade.getSections());
    }
}
