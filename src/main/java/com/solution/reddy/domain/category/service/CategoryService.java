package com.solution.reddy.domain.category.service;

import com.solution.reddy.domain.category.dto.CategoryRequestDto;
import com.solution.reddy.domain.category.dto.CategoryResponseDto;
import com.solution.reddy.domain.category.entity.CategoryEntity;
import com.solution.reddy.domain.category.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponseDto> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryEntity::toCategoryResponseDto)
                .toList();
    }

    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        return categoryRepository
                .save(categoryRequestDto.makeCategoryEntity())
                .toCategoryResponseDto();
    }

    public CategoryEntity findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }
}
