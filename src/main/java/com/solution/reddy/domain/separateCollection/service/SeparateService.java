package com.solution.reddy.domain.separateCollection.service;

import com.solution.reddy.domain.category.entity.CategoryEntity;
import com.solution.reddy.domain.category.service.CategoryService;
import com.solution.reddy.domain.separateCollection.dto.SeparatePostRequestDto;
import com.solution.reddy.domain.separateCollection.dto.response.SeparatePostResponseDto;
import com.solution.reddy.domain.separateCollection.entity.SeparateEntity;
import com.solution.reddy.domain.separateCollection.repository.SeparateRepository;
import com.solution.reddy.global.exception.ApiException;
import com.solution.reddy.global.message.CategoryMessage;
import com.solution.reddy.global.message.SeparateMessage;
import java.util.List;
import java.util.Optional;
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

    public List<SeparatePostResponseDto> getSeparateByCategory(Long categoryName) {
        CategoryEntity category = categoryService.findByCategoryId(categoryName);
        Optional<List<SeparatePostResponseDto>> list = separateRepository.findByCategory(category);
        if (list.isEmpty()) {
            throw new ApiException(SeparateMessage.POST_NOT_FOUND);
        }
        return list.get();
    }
}
