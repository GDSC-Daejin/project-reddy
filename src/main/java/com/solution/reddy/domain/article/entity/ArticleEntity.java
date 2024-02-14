package com.solution.reddy.domain.article.entity;

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
    private String title;
    private String contents;
    private String articleImageUrl;

    private Long goodCount;
    private Long sadCount;
    private Long angryCount;
    private Long sosoCount;


    @Builder
    public ArticleEntity createArticleEntity(String title, String contents, String articleImageUrl) {
        this.title = title;
        this.contents = contents;
        this.articleImageUrl = articleImageUrl;

        this.goodCount = 0L;
        this.sadCount = 0L;
        this.angryCount = 0L;
        this.sosoCount = 0L;
        return this;
    }

    public DetailArticleDto toDetailArticleDto() {
        return DetailArticleDto.builder()
                .title(this.title)
                .contents(this.contents)
                .goodCount(this.goodCount)
                .sadCount(this.sadCount)
                .angryCount(this.angryCount)
                .sosoCount(this.sosoCount)
                .build();
    }

    public ArticleTitleItems toArticleTitleItems() {
        return ArticleTitleItems.builder()
                .id(this.id)
                .title(this.title)
                .build();
    }
}
