package com.solution.reddy.domain.category.service;

import com.solution.reddy.domain.category.dto.CategoryRequestDto;
import com.solution.reddy.domain.category.dto.CategoryResponseDto;
import com.solution.reddy.domain.category.entity.CategoryEntity;
import com.solution.reddy.domain.category.repository.CategoryRepository;
import com.solution.reddy.global.exception.ApiException;
import com.solution.reddy.global.message.CategoryMessage;
import java.util.List;
import java.util.Optional;
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
        Optional<CategoryEntity> category = categoryRepository.findByCategoryName(categoryName);
        if(category.isEmpty()) {
            throw new ApiException(CategoryMessage.CATEGORY_NOT_FOUND);
        }
        return category.get();
    }

    public CategoryEntity findByCategoryId(long categoryId) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
        isEmpty(category);
        return category.get();
    }

    private void isEmpty(Optional<CategoryEntity> category) {
        if(category.isEmpty()) {
            throw new ApiException(CategoryMessage.CATEGORY_NOT_FOUND);
        }
    }
}
