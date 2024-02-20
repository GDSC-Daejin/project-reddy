package com.solution.reddy.domain.article.repository;

import com.solution.reddy.domain.article.entity.ArticleEmotionEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArticleEmotionRepository extends JpaRepository<ArticleEmotionEntity, Long> {
    @Query("SELECT a FROM ArticleEmotionEntity a WHERE a.article.id = :id")
    Optional<ArticleEmotionEntity> findByArticleId(Long id);
}
