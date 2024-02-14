package com.solution.reddy.domain.article.service;

import com.solution.reddy.domain.article.repository.ArticleRepository;
import com.solution.reddy.domain.article.dto.response.ArticleTitleItems;
import com.solution.reddy.domain.article.dto.response.ArticleTitleResponseDto;
import com.solution.reddy.domain.article.dto.response.DetailArticleDto;
import com.solution.reddy.domain.article.entity.ArticleEntity;
import com.solution.reddy.global.dto.PageResponse;
import com.solution.reddy.global.exception.ApiException;
import com.solution.reddy.global.message.ArticleMessage;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public DetailArticleDto getDetailArticle(Long id) {
        Optional<ArticleEntity> article = articleRepository.findById(id);
        if(article.isEmpty()) {
            throw new ApiException(ArticleMessage.ARTICLE_NOT_FOUD);
        }
        return article.get().toDetailArticleDto();
    }

    public ArticleTitleResponseDto getArticleTitle(Pageable pageable) {
        Page<ArticleTitleItems> articleTitlePage = articleRepository.findAll(pageable)
                                                                    .map(ArticleEntity::toArtitleTitleItems);
        PageResponse pageResponse = new PageResponse(articleTitlePage);
        if (articleTitlePage.isEmpty()) {
            throw new ApiException(ArticleMessage.ARTICLE_IS_EMPTY);
        }
        return new ArticleTitleResponseDto(articleTitlePage.toList(), pageResponse);
    }
}
