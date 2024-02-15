package com.solution.reddy.domain.separateCollection.service;

import com.solution.reddy.domain.category.entity.CategoryEntity;
import com.solution.reddy.domain.category.service.CategoryService;
import com.solution.reddy.domain.separateCollection.dto.SeparatePostRequestDto;
import com.solution.reddy.domain.separateCollection.entity.SeparateEntity;
import com.solution.reddy.domain.separateCollection.repository.SeparateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeparateService {
    private final SeparateRepository separateRepository;
    private final CategoryService categoryService;


    public Long createSeparatePost(SeparatePostRequestDto requestDto) {
        CategoryEntity category = categoryService.findByCategoryName(requestDto.category());
        SeparateEntity entity = requestDto.toSeparateEntity(category);
        return separateRepository.save(entity).getId();
    }

}
