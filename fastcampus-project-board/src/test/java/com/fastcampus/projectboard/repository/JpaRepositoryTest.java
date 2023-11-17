package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.config.JpaConfig;
import com.fastcampus.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;



@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(@Autowired ArticleRepository articleRepository, @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @Test
    @DisplayName("select 테스트")
    void givenTestData_whenSelecting_ThenWorksFine() {

        // given

        // When
        List<Article> articles = articleRepository.findAll();
        // Then
        assertThat(articles)
                .isNotNull()
                .hasSize(123);
    }

    @Test
    @DisplayName("insert 테스트")
    void givenTestData_whenInserting_ThenWorksFine() {

        // given
        long previousCount = articleRepository.count();

        // When
        Article savedArticle = articleRepository.save(Article.of("new article","new content","#spring"));
        // Then
        assertThat(articleRepository.count()).isEqualTo(previousCount+1);
    }

    @Test
    @DisplayName("update 테스트")
    void givenTestData_whenUpdating_ThenWorksFine() {

        // given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHashtag = "springboot";
        article.setHashtag(updateHashtag);

        // When
        Article savedArticle = articleRepository.saveAndFlush(article);

        // Then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag",updateHashtag);
    }

    @Test
    @DisplayName("delete 테스트")
    void givenTestData_whenDeleting_ThenWorksFine() {

        // given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentSize = article.getArticleComments().size();

        // When
        articleRepository.delete(article);

        // Then
    assertThat(articleRepository.count()).isEqualTo(previousArticleCount-1);
    assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount-deletedCommentSize);

    }
}