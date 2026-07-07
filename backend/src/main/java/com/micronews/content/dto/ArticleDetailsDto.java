package com.micronews.content.dto;

import java.time.LocalDateTime;

public record ArticleDetailsDto(
        Integer id,
        String category,
        String title,
        String leadImage,
        String content,
        LocalDateTime publicationDate
) {}
