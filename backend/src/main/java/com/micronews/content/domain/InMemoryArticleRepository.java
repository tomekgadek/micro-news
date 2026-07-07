package com.micronews.content.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

class InMemoryArticleRepository implements ArticleRepository {
    private final ConcurrentHashMap<Integer, Article> map = new ConcurrentHashMap<>();
    private final AtomicInteger seq = new AtomicInteger(1);

    @Override
    public Article save(Article article) {
        requireNonNull(article);
        if (article.id == null) {
            article = new Article(seq.getAndIncrement(), article.title, article.content, article.dateArt, article.idSection);
        }
        map.put(article.id, article);
        return article;
    }

    @Override
    public Article findById(Integer id) {
        return map.get(id);
    }

    @Override
    public List<Article> findLatestArticlesPerCategory(org.springframework.data.domain.Pageable pageable) {
        java.util.Map<Integer, java.time.LocalDateTime> maxDates = new java.util.HashMap<>();
        for (Article a : map.values()) {
            if (a.idSection != null && a.dateArt != null) {
                java.time.LocalDateTime currentMax = maxDates.get(a.idSection);
                if (currentMax == null || a.dateArt.isAfter(currentMax)) {
                    maxDates.put(a.idSection, a.dateArt);
                }
            }
        }

        return map.values().stream()
                .filter(a -> a.idSection != null && a.dateArt != null && a.dateArt.equals(maxDates.get(a.idSection)))
                .sorted((a1, a2) -> a2.dateArt.compareTo(a1.dateArt))
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
     }

    @Override
    public Page<Article> findAll(Pageable pageable) {
        java.util.Comparator<Article> comparator = (a1, a2) -> {
            if (a1.dateArt == null && a2.dateArt == null) return 0;
            if (a1.dateArt == null) return 1;
            if (a2.dateArt == null) return -1;
            return a2.dateArt.compareTo(a1.dateArt);
        };
        
        if (pageable.getSort() != null && pageable.getSort().isSorted()) {
            org.springframework.data.domain.Sort.Order order = pageable.getSort().getOrderFor("dateArt");
            if (order != null) {
                if (order.isAscending()) {
                    comparator = (a1, a2) -> {
                        if (a1.dateArt == null && a2.dateArt == null) return 0;
                        if (a1.dateArt == null) return -1;
                        if (a2.dateArt == null) return 1;
                        return a1.dateArt.compareTo(a2.dateArt);
                    };
                } else {
                    comparator = (a1, a2) -> {
                        if (a1.dateArt == null && a2.dateArt == null) return 0;
                        if (a1.dateArt == null) return 1;
                        if (a2.dateArt == null) return -1;
                        return a2.dateArt.compareTo(a1.dateArt);
                    };
                }
            }
        }

        List<Article> sorted = map.values().stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), sorted.size());
        List<Article> subList = (start > sorted.size()) ? List.of() : sorted.subList(start, end);

        return new PageImpl<>(subList, pageable, sorted.size());
    }
}
