package com.solution.reddy.domain.article.entity;

import com.solution.reddy.domain.user.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity @Table(name = "tb_article_emotion")
@AllArgsConstructor
public class ArticleEmotionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private ArticleEntity article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Boolean isSosoEmotion;
    private Boolean isAnalysisEmotion;
    private Boolean isGoodEmotion;
    private Boolean isEmpathyEmotion;

    @Builder
    public ArticleEmotionEntity(ArticleEntity article, UserEntity user) {
        this.article = article;
        this.user = user;
        this.isSosoEmotion = false;
        this.isAnalysisEmotion = false;
        this.isGoodEmotion = false;
        this.isEmpathyEmotion = false;
    }

    public void changeSosoEmotion() {
        this.isSosoEmotion = !this.isSosoEmotion;
    }

    public void changeAnalysisEmotion() {
        this.isAnalysisEmotion = !this.isAnalysisEmotion;
    }

    public void changeGoodEmotion() {
        this.isGoodEmotion = !this.isGoodEmotion;
    }

    public void changeEmpathyEmotion() {
        this.isEmpathyEmotion = !this.isEmpathyEmotion;
    }
}
