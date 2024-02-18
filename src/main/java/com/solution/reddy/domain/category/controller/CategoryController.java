package com.solution.reddy.domain.category.controller;

import com.solution.reddy.domain.category.controller.springdocs.CreateCategorySpringDocs;
import com.solution.reddy.domain.category.controller.springdocs.GetCategoriesSpringDocs;
import com.solution.reddy.domain.category.dto.CategoryRequestDto;
import com.solution.reddy.domain.category.dto.CategoryResponseDto;
import com.solution.reddy.domain.category.service.CategoryService;
import com.solution.reddy.global.controller.FirstVersionRestController;
import com.solution.reddy.global.dto.ReddyApiResponse;
import com.solution.reddy.global.message.CategoryMessage;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "카테고리 컨트롤러")
@FirstVersionRestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/category")
    @GetCategoriesSpringDocs
    public ReddyApiResponse<List<CategoryResponseDto>> getCategories() {
        List<CategoryResponseDto> categories = categoryService.findAllCategories();
        return ReddyApiResponse.createResponse(categories, CategoryMessage.CATEGORY_FIND_ALL_SUCCESS);
    }

    @PostMapping("/category")
    @CreateCategorySpringDocs
    public ReddyApiResponse<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto category = categoryService.createCategory(categoryRequestDto);
        return ReddyApiResponse.createResponse(category, CategoryMessage.CATECORY_CREATE_SUCCESS);
    }
}
