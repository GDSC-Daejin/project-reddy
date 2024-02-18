package com.solution.reddy.domain.separateCollection.repository;

import com.solution.reddy.domain.category.entity.CategoryEntity;
import com.solution.reddy.domain.separateCollection.entity.SeparateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SeparateRepository extends JpaRepository<SeparateEntity, Long> {

    Page<SeparateEntity> findAllByCategory(CategoryEntity category, Pageable pageable);

    @Query("select s from SeparateEntity s, CategoryEntity c where s.title like concat('%', :keyword, '%') "
            + "and s.description like concat('%', :keyword, '%')")
    Page<SeparateEntity> searchSeparatePost(String keyword, Pageable pageable);
}
