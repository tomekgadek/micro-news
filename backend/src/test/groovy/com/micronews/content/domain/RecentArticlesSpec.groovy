package com.micronews.content.domain

import com.micronews.content.dto.PublicArticleDto
import com.micronews.content.dto.ArticleSummaryDto
import com.micronews.content.dto.ArticleDetailsDto
import com.micronews.content.dto.ArticleNotFoundException
import com.micronews.media.domain.MediaConfiguration
import com.micronews.media.domain.MediaFacade
import com.micronews.media.domain.Image
import java.time.LocalDateTime
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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
        result[index].id() == id
        result[index].title() == title
        result[index].category() == category
        result[index].leadImage() == leadImage
        result[index].content() == content
        result[index].publicationDate() == date

        where:
        index | id | title                | category | leadImage                      | content          | date
        0     | 1  | "Lewandowski w MLS"  | "Sport"  | "http://image-lewandowski.jpg" | "Treść sportowa" | LocalDateTime.of(2026, 7, 5, 10, 0)
        1     | 3  | "Premiera Lalki"     | "Film"   | "http://image-lalka.jpg"       | "Treść filmowa"  | LocalDateTime.of(2026, 7, 3, 10, 0)
        2     | 4  | "Hadron LHC"         | "Nauka"  | null                           | "Treść naukowa"  | LocalDateTime.of(2026, 7, 2, 10, 0)
    }

    def "should return paginated articles ordered by date descending without content"() {
        given: "sections"
        def sport = contentFacade.sectionRepository.save(new Section(null, "Sport"))

        and: "articles"
        def art1 = contentFacade.articleRepository.save(new Article(null, "A1", "Content 1", LocalDateTime.of(2026, 7, 1, 12, 0), sport.id))
        def art2 = contentFacade.articleRepository.save(new Article(null, "A2", "Content 2", LocalDateTime.of(2026, 7, 3, 12, 0), sport.id))
        def art3 = contentFacade.articleRepository.save(new Article(null, "A3", "Content 3", LocalDateTime.of(2026, 7, 2, 12, 0), sport.id))

        and: "associated images"
        mediaFacade.imageRepository.save(new Image(null, "http://image1.jpg", "A1 img", [art1.id] as Set))
        mediaFacade.imageRepository.save(new Image(null, "http://image3.jpg", "A3 img", [art3.id] as Set))

        when: "we request first page with size 2"
        def pageRequest = PageRequest.of(0, 2, Sort.by("dateArt").descending())
        def result = contentFacade.getArticles(pageRequest)

        then: "result has correct metadata"
        result.totalElements == 3
        result.totalPages == 2
        result.content.size() == 2

        and: "articles are ordered by date descending (art2 then art3)"
        result.content[0].id() == art2.id
        result.content[0].title() == "A2"
        result.content[0].category() == "Sport"
        result.content[0].leadImage() == null
        result.content[0].publicationDate() == LocalDateTime.of(2026, 7, 3, 12, 0)

        result.content[1].id() == art3.id
        result.content[1].title() == "A3"
        result.content[1].category() == "Sport"
        result.content[1].leadImage() == "http://image3.jpg"
        result.content[1].publicationDate() == LocalDateTime.of(2026, 7, 2, 12, 0)

        and: "the returned objects are ArticleSummaryDto and do not contain content field"
        result.content[0] instanceof ArticleSummaryDto
        !result.content[0].metaClass.respondsTo(result.content[0], "content")
    }

    def "should return article details"() {
        given: "a section and article"
        def sport = contentFacade.sectionRepository.save(new Section(null, "Sport"))
        def art = contentFacade.articleRepository.save(new Article(null, "A1", "Content 1", LocalDateTime.of(2026, 7, 1, 12, 0), sport.id))
        mediaFacade.imageRepository.save(new Image(null, "http://image1.jpg", "A1 img", [art.id] as Set))

        when: "we request details"
        def details = contentFacade.getArticleDetails(art.id)

        then: "we get full details including content"
        details.id() == art.id
        details.title() == "A1"
        details.category() == "Sport"
        details.leadImage() == "http://image1.jpg"
        details.content() == "Content 1"
        details.publicationDate() == LocalDateTime.of(2026, 7, 1, 12, 0)
    }

    def "should throw ArticleNotFoundException when article does not exist"() {
        when: "we request details for non-existent ID"
        contentFacade.getArticleDetails(999)

        then: "ArticleNotFoundException is thrown"
        thrown(ArticleNotFoundException)
    }

    def "should return empty list of sections when none exist"() {
        when: "we request all sections"
        def result = contentFacade.getSections()

        then: "result is empty"
        result.isEmpty()
    }

    def "should return all sections"() {
        given: "saved sections"
        def sport = contentFacade.sectionRepository.save(new Section(null, "Sport"))
        def film = contentFacade.sectionRepository.save(new Section(null, "Film"))

        when: "we request all sections"
        def result = contentFacade.getSections()

        then: "we receive all saved sections correctly mapped"
        result.size() == 2
        result.find { it.id() == sport.id }.name() == "Sport"
        result.find { it.id() == film.id }.name() == "Film"
    }
}
