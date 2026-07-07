package com.micronews.content.dto;

import java.time.LocalDateTime;

public record ArticleSummaryDto(
        Integer id,
        String category,
        String title,
        String leadImage,
        LocalDateTime publicationDate
) {}
