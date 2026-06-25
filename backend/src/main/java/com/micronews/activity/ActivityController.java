package com.micronews.activity;

import com.micronews.activity.domain.ActivityFacade;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ActivityController {
    private final ActivityFacade activityFacade;

    public ActivityController(ActivityFacade activityFacade) {
        this.activityFacade = activityFacade;
    }
}
