package com.micronews.activity.dto;

import java.time.LocalDateTime;

public record ArticleUserDto(Integer idArticle, Integer idUser, LocalDateTime dateRead) {}
