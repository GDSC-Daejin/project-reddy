package com.solution.reddy.domain.separateCollection.repository;

import com.solution.reddy.domain.category.entity.CategoryEntity;
import com.solution.reddy.domain.separateCollection.dto.response.SeparatePostResponseDto;
import com.solution.reddy.domain.separateCollection.entity.SeparateEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeparateRepository extends JpaRepository<SeparateEntity, Long> {

    Optional<List<SeparatePostResponseDto>> findAllByCategory(CategoryEntity category);
}
