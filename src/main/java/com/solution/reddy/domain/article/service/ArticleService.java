package com.solution.reddy.domain.article.service;

import static com.solution.reddy.domain.article.dto.request.ArticleEmotion.ANALYSIS;
import static com.solution.reddy.domain.article.dto.request.ArticleEmotion.EMPATHY;
import static com.solution.reddy.domain.article.dto.request.ArticleEmotion.GOOD;
import static com.solution.reddy.domain.article.dto.request.ArticleEmotion.SOSO;

import com.solution.reddy.domain.article.dto.request.ArticleEmotion;
import com.solution.reddy.domain.article.entity.ArticleEmotionEntity;
import com.solution.reddy.domain.article.repository.ArticleEmotionRepository;
import com.solution.reddy.domain.article.repository.ArticleRepository;
import com.solution.reddy.domain.article.dto.response.ArticleTitleItems;
import com.solution.reddy.domain.article.dto.response.ArticleTitleResponseDto;
import com.solution.reddy.domain.article.dto.response.DetailArticleDto;
import com.solution.reddy.domain.article.entity.ArticleEntity;
import com.solution.reddy.domain.user.entity.UserEntity;
import com.solution.reddy.domain.user.repository.UserRepository;
import com.solution.reddy.global.dto.PageResponse;
import com.solution.reddy.global.exception.ApiException;
import com.solution.reddy.global.message.ArticleMessage;
import com.solution.reddy.global.message.UserMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleEmotionRepository articleEmotionRepository;
    private final UserRepository userRepository;

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


    public List<ArticleTitleItems> getTodaysArticle() {
        Pageable pageable = PageRequest.of(0, 10)
                .withSort(Sort.by(Sort.Direction.DESC, "goodCount"));
        Page<ArticleTitleItems> articleTitlePage = articleRepository.findAll(pageable)
                                                                    .map(ArticleEntity::toArticleTitleItems);
        if (articleTitlePage.isEmpty()) {
            throw new ApiException(ArticleMessage.ARTICLE_IS_EMPTY);
        }
        return articleTitlePage.toList();
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

    public void clickArticleEmotion(Long id, ArticleEmotion emotion, String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if(user.isEmpty()) {
            throw new ApiException(UserMessage.USER_NOT_FOUND);
        }

        Optional<ArticleEntity> article = articleRepository.findById(id);
        if(article.isEmpty()) {
            throw new ApiException(ArticleMessage.ARTICLE_NOT_FOUD);
        }

        Optional<ArticleEmotionEntity> articleEmotion = articleEmotionRepository.findByArticleId(id);
        if(articleEmotion.isEmpty()) {
            ArticleEmotionEntity newArticleEmotion = new ArticleEmotionEntity(article.get(), user.get());
            clickEmotion(newArticleEmotion, emotion, article.get());
            articleEmotionRepository.save(newArticleEmotion);
            return;
        }

        clickEmotion(articleEmotion.get(), emotion, article.get());
    }

    private void clickEmotion(ArticleEmotionEntity articleEmotionEntity, ArticleEmotion emotion, ArticleEntity targetArticle) {
        if (emotion.equals(SOSO)) {
            if (articleEmotionEntity.getIsSosoEmotion()) { // 이미 클릭한 이모지인 경우
                updateEmotionCountMinus(targetArticle, emotion);
            } else {
                updateEmotionCountPlus(targetArticle, emotion);
            }
            articleEmotionEntity.changeSosoEmotion();
        } else if (emotion.equals(ANALYSIS)) {
            if (articleEmotionEntity.getIsAnalysisEmotion()) {
                updateEmotionCountMinus(targetArticle, emotion);
            } else {
                updateEmotionCountPlus(targetArticle, emotion);
            }
            articleEmotionEntity.changeAnalysisEmotion();
        } else if (emotion.equals(GOOD)) {
            if (articleEmotionEntity.getIsGoodEmotion()) {
                updateEmotionCountMinus(targetArticle, emotion);
            } else {
                updateEmotionCountPlus(targetArticle, emotion);
            }
            articleEmotionEntity.changeGoodEmotion();
        } else if (emotion.equals(EMPATHY)) {
            if (articleEmotionEntity.getIsEmpathyEmotion()) {
                updateEmotionCountPlus(targetArticle, emotion);
            } else {
                updateEmotionCountMinus(targetArticle, emotion);
            }
            articleEmotionEntity.changeEmpathyEmotion();
        } else {
            throw new ApiException(ArticleMessage.EMOTION_NOT_FOUND);
        }
        articleRepository.save(targetArticle);
    }
    private void updateEmotionCountPlus(ArticleEntity article, ArticleEmotion emotion) {
        article.updateEmotionCount(emotion, 1L);
    }

    public void updateEmotionCountMinus(ArticleEntity article, ArticleEmotion emotion) {
        article.updateEmotionCount(emotion, -1L);
    }
}
