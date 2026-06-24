package com.micronews.content.dto;

import java.time.LocalDateTime;

public record ArticleDto(Integer id, String title, String content, LocalDateTime dateArt, Integer idSection) {}
