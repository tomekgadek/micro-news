package com.micronews.content;

import com.micronews.content.domain.ContentFacade;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ContentController {
    private final ContentFacade contentFacade;

    public ContentController(ContentFacade contentFacade) {
        this.contentFacade = contentFacade;
    }
}
