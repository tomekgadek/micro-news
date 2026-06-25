package com.micronews.media;

import com.micronews.media.domain.MediaFacade;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MediaController {
    private final MediaFacade mediaFacade;

    public MediaController(MediaFacade mediaFacade) {
        this.mediaFacade = mediaFacade;
    }
}
