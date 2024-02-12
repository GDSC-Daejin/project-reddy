package com.solution.reddy.domain.article.controller;

import com.solution.reddy.domain.article.dto.response.DetailArticleDto;
import com.solution.reddy.domain.article.service.ArticleService;
import com.solution.reddy.global.controller.FirstVersionRestController;
import com.solution.reddy.global.dto.ReddyApiResponse;
import com.solution.reddy.global.message.ArticleMessage;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "뉴스 기사 관련 컨트롤러")
@FirstVersionRestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping("/article/{id}")
    public ReddyApiResponse<DetailArticleDto> getArticleDetail(@PathVariable Long id) {
        DetailArticleDto article = articleService.getDetailArticle(id);
        return ReddyApiResponse.createResponse(article, ArticleMessage.GET_ARTICLE_DETAIL_SUCCESS);
    }
}
