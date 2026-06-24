package com.micronews.media.dto;

import java.util.Set;

public record ImageDto(Integer id, String href, String title, Set<Integer> articleIds) {}
