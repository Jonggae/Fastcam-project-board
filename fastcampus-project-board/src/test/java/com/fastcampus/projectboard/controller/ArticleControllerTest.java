package com.fastcampus.projectboard.controller;

import com.fastcampus.projectboard.domain.ArticleComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(ArticleComment.class)
class ArticleControllerTest {

    private final MockMvc mvc;

    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    @DisplayName("[view] [GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
    public void givenNothing_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
        //given

        //when & then
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.model().attributeExists("articles"));
    }
    @Test
    @DisplayName("[view] [GET] 게시글 상세 (게시판) 페이지 - 정상 호출")
    public void givenNothing_whenRequestingArticleView_thenReturnsArticleView() throws Exception {
        //given

        //when & then
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.model().attributeExists("article"));
    }
    @Test
    @DisplayName("[view] [GET] 게시글 검색 전용 페이지 - 정상 호출")
    public void givenNothing_whenRequestingArticleSearchView_thenReturnsArticleSearchView() throws Exception {
        //given

        //when & then
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.TEXT_HTML));
    }

    @Test
    @DisplayName("[view] [GET] 게시글 해시태그 검색  페이지 - 정상 호출")
    public void givenNothing_whenRequestingArticleHashtagSearchView_thenReturnsArticleHashtagSearchView() throws Exception {
        //given

        //when & then
        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.TEXT_HTML));
    }
}