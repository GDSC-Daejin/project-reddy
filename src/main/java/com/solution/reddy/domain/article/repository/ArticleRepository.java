package com.solution.reddy.domain.article.repository;

import com.solution.reddy.domain.article.dto.response.ArticleTitleItems;
import com.solution.reddy.domain.article.entity.ArticleEmotionEntity;
import com.solution.reddy.domain.article.entity.ArticleEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Streamable;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    @Query("SELECT a FROM ArticleEntity a ORDER BY a.goodCount DESC ")
    List<ArticleEntity> findTodaysArticle();
}
