package com.solution.reddy.domain.article.service;

import com.solution.reddy.domain.article.repository.ArticleRepository;
import com.solution.reddy.domain.article.dto.response.ArticleTitleItems;
import com.solution.reddy.domain.article.dto.response.ArticleTitleResponseDto;
import com.solution.reddy.domain.article.dto.response.DetailArticleDto;
import com.solution.reddy.domain.article.entity.ArticleEntity;
import com.solution.reddy.global.dto.PageResponse;
import com.solution.reddy.global.exception.ApiException;
import com.solution.reddy.global.message.ArticleMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.StringTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
                                                                    .map(ArticleEntity::toArticleTitleItems);
        PageResponse pageResponse = new PageResponse(articleTitlePage);
        if (articleTitlePage.isEmpty()) {
            throw new ApiException(ArticleMessage.ARTICLE_IS_EMPTY);
        }
        return new ArticleTitleResponseDto(articleTitlePage.toList(), pageResponse);
    }

    public void uploadFile(MultipartFile file) {
        // 파일 업로드 로직
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
            String line = br.readLine();
            while (line != null) {
                StringTokenizer st = new StringTokenizer(br.readLine(), ",");
                String date = st.nextToken();
                String title = st.nextToken();
                String link = st.nextToken();
                String content = st.nextToken();
                String imageUrl = st.nextToken();
                if (imageUrl.equals("이미지 없음")) {
                    imageUrl = null;
                }

                ArticleEntity article = new ArticleEntity(date, title, link, content, imageUrl);
                articleRepository.save(article);

                line = br.readLine();
            }
        } catch (IOException e) {
            throw new ApiException(ArticleMessage.ARTICLE_POST_FAIL);
        }
    }
}
