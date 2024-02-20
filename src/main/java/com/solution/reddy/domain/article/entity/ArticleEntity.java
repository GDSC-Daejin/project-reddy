package com.solution.reddy.domain.article.entity;

import static com.solution.reddy.domain.article.dto.request.ArticleEmotion.ANALYSIS;
import static com.solution.reddy.domain.article.dto.request.ArticleEmotion.EMPATHY;
import static com.solution.reddy.domain.article.dto.request.ArticleEmotion.GOOD;
import static com.solution.reddy.domain.article.dto.request.ArticleEmotion.SOSO;

import com.solution.reddy.domain.article.dto.request.ArticleEmotion;
import com.solution.reddy.domain.article.dto.response.ArticleTitleItems;
import com.solution.reddy.domain.article.dto.response.DetailArticleDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_article")
@AllArgsConstructor
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;
    private String date;
    private String title;
    private String link;
    @Column(length = 50000)
    private String contents;
    private String articleImageUrl;

    private Long sosoCount;
    private Long analysisCount;
    private Long goodCount;
    private Long empathyCount;

    public void updateEmotionCount(ArticleEmotion emotion, Long count) {
        if (emotion.equals(SOSO)) {
            this.sosoCount+= count;
        } else if (emotion.equals(ANALYSIS)) {
            this.analysisCount+= count;
        } else if (emotion.equals(GOOD)) {
            this.goodCount+= count;
        } else if (emotion.equals(EMPATHY)) {
            this.empathyCount+= count;
        }
    }


    @Builder
    public ArticleEntity (String date, String title, String link, String contents, String articleImageUrl) {
        this.date = date;
        this.title = title;
        this.link = link;
        this.contents = contents;
        this.articleImageUrl = articleImageUrl;

        this.sosoCount = 0L;
        this.analysisCount = 0L;
        this.goodCount = 0L;
        this.empathyCount = 0L;
    }

    public DetailArticleDto toDetailArticleDto(ArticleEmotionEntity articleEmotionEntity) {
        return DetailArticleDto.builder()
                .date(this.date)
                .title(this.title)
                .contents(this.contents)
                .isSosoEmotion(articleEmotionEntity.getIsSosoEmotion())
                .isAnalysisEmotion(articleEmotionEntity.getIsAnalysisEmotion())
                .isGoodEmotion(articleEmotionEntity.getIsGoodEmotion())
                .isEmpathyEmotion(articleEmotionEntity.getIsEmpathyEmotion())
                .build();
    }

    public ArticleTitleItems toArticleTitleItems() {
        return ArticleTitleItems.builder()
                .id(this.id)
                .title(this.title)
                .imageUrl(this.articleImageUrl)
                .build();
    }
}
