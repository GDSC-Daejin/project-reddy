package com.solution.reddy.domain.article.controller;

import com.solution.reddy.domain.article.controller.springdocs.ClickArticleEmotionSpringDocs;
import com.solution.reddy.domain.article.controller.springdocs.GetArticleDetailSpringDocs;
import com.solution.reddy.domain.article.controller.springdocs.GetArticleTitleSpringDocs;
import com.solution.reddy.domain.article.controller.springdocs.GetTodaysArticleTitleSpringDocs;
import com.solution.reddy.domain.article.dto.request.ArticleEmotionRequest;
import com.solution.reddy.domain.article.dto.response.ArticleTitleItems;
import com.solution.reddy.domain.article.dto.response.ArticleTitleResponseDto;
import com.solution.reddy.domain.article.dto.response.DetailArticleDto;
import com.solution.reddy.domain.article.service.ArticleService;
import com.solution.reddy.global.controller.FirstVersionRestController;
import com.solution.reddy.global.dto.ReddyApiResponse;
import com.solution.reddy.global.jwt.dto.UserInfo;
import com.solution.reddy.global.message.ArticleMessage;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "뉴스 기사 관련 컨트롤러")
@FirstVersionRestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetArticleDetailSpringDocs
    @GetMapping("/article/{id}")
    public ReddyApiResponse<DetailArticleDto> getArticleDetail(@PathVariable Long id) {
        DetailArticleDto article = articleService.getDetailArticle(id);
        return ReddyApiResponse.createResponse(article, ArticleMessage.GET_ARTICLE_DETAIL_SUCCESS);
    }

    @GetArticleTitleSpringDocs
    @GetMapping("/article")
    public ReddyApiResponse<ArticleTitleResponseDto> getArticleTitle(@PageableDefault @Parameter(hidden = true) Pageable pageable) {
        ArticleTitleResponseDto article = articleService.getArticleTitle(pageable);
        return ReddyApiResponse.createResponse(article, ArticleMessage.GET_ARTICLE_TITLE_SUCCESS);
    }

    @GetMapping("/article/today")
    @GetTodaysArticleTitleSpringDocs
    public ReddyApiResponse<List<ArticleTitleItems>> getTodaysArticle() {
        List<ArticleTitleItems> article = articleService.getTodaysArticle();
        return ReddyApiResponse.createResponse(article, ArticleMessage.GET_TODAYS_ARTICLE_TITLE_SUCCESS);
    }

    @PostMapping(value = "/article", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ReddyApiResponse<?> postArticle(@ModelAttribute MultipartFile file){
        articleService.uploadFile(file);
        return ReddyApiResponse.createResponse(null, ArticleMessage.ARTICLE_POST_SUCCESS);
    }

    @PostMapping("/article/emotion")
    @ClickArticleEmotionSpringDocs
    public ReddyApiResponse<?> clickArticleEmotion(@RequestBody ArticleEmotionRequest request,
                                                   @AuthenticationPrincipal UserInfo user) {
        articleService.clickArticleEmotion(request.articleId(), request.emotion(), user.getEmail());
        return ReddyApiResponse.createResponse(null, ArticleMessage.ARTICLE_EMOTION_POST_SUCCESS);
    }
}
