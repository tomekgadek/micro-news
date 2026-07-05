package com.micronews.content.domain

import com.micronews.content.dto.PublicArticleDto
import com.micronews.media.domain.MediaConfiguration
import com.micronews.media.domain.MediaFacade
import com.micronews.media.domain.Image
import java.time.LocalDateTime
import spock.lang.Specification

class RecentArticlesSpec extends Specification {

    MediaFacade mediaFacade = new MediaConfiguration().mediaFacade()
    ContentFacade contentFacade = new ContentConfiguration().contentFacade(mediaFacade)

    def "should return empty list when no articles exist"() {
        when: "we request recent public articles"
        def result = contentFacade.getRecentPublicArticles()

        then: "result is empty"
        result.isEmpty()
    }

    def "should return articles from different categories ordered by date descending"() {
        given: "sections (categories)"
        def sport = contentFacade.sectionRepository.save(new Section(null, "Sport"))
        def film = contentFacade.sectionRepository.save(new Section(null, "Film"))
        def nauka = contentFacade.sectionRepository.save(new Section(null, "Nauka"))
        def polityka = contentFacade.sectionRepository.save(new Section(null, "Polityka"))

        and: "articles with varying dates and sections"
        // 2026-07-05 (newest, Sport)
        def artA = contentFacade.articleRepository.save(new Article(null, "Lewandowski w MLS", "Treść sportowa", LocalDateTime.of(2026, 7, 5, 10, 0), sport.id))
        // 2026-07-04 (older Sport - should be skipped)
        def artB = contentFacade.articleRepository.save(new Article(null, "Hurkacz wygrywa", "Treść sportowa 2", LocalDateTime.of(2026, 7, 4, 10, 0), sport.id))
        // 2026-07-03 (Film)
        def artC = contentFacade.articleRepository.save(new Article(null, "Premiera Lalki", "Treść filmowa", LocalDateTime.of(2026, 7, 3, 10, 0), film.id))
        // 2026-07-02 (Nauka)
        def artD = contentFacade.articleRepository.save(new Article(null, "Hadron LHC", "Treść naukowa", LocalDateTime.of(2026, 7, 2, 10, 0), nauka.id))
        // 2026-07-01 (oldest, Polityka - should be skipped because we already have 3 categories: Sport, Film, Nauka)
        def artE = contentFacade.articleRepository.save(new Article(null, "Wybory", "Treść polityczna", LocalDateTime.of(2026, 7, 1, 10, 0), polityka.id))

        and: "associated images"
        mediaFacade.imageRepository.save(new Image(null, "http://image-lewandowski.jpg", "Lewy", [artA.id] as Set))
        mediaFacade.imageRepository.save(new Image(null, "http://image-lalka.jpg", "Lalka", [artC.id] as Set))
        // artD has no image

        when: "we request recent public articles"
        List<PublicArticleDto> result = contentFacade.getRecentPublicArticles()

        then: "we receive exactly 3 articles"
        result.size() == 3

        and: "the articles match the expected properties at each index"
        result[index].title() == title
        result[index].category() == category
        result[index].leadImage() == leadImage
        result[index].content() == content
        result[index].publicationDate() == date

        where:
        index | title                | category | leadImage                      | content          | date
        0     | "Lewandowski w MLS"  | "Sport"  | "http://image-lewandowski.jpg" | "Treść sportowa" | LocalDateTime.of(2026, 7, 5, 10, 0)
        1     | "Premiera Lalki"     | "Film"   | "http://image-lalka.jpg"       | "Treść filmowa"  | LocalDateTime.of(2026, 7, 3, 10, 0)
        2     | "Hadron LHC"         | "Nauka"  | null                           | "Treść naukowa"  | LocalDateTime.of(2026, 7, 2, 10, 0)
    }
}
