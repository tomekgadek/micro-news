package com.micronews.content.dto;

import java.time.LocalDateTime;

public record PublicArticleDto(
        String category,
        String title,
        String leadImage,
        String content,
        LocalDateTime publicationDate
) {}
