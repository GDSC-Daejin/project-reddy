package com.solution.reddy.domain.article.service;

import com.solution.reddy.domain.article.controller.repository.ArticleRepository;
import com.solution.reddy.domain.article.dto.response.DetailArticleDto;
import com.solution.reddy.domain.article.entity.ArticleEntity;
import com.solution.reddy.global.exception.ApiException;
import com.solution.reddy.global.message.ArticleMessage;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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
}
