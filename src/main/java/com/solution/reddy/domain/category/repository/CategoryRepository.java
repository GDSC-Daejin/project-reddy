package com.solution.reddy.domain.category.repository;

import com.solution.reddy.domain.category.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
