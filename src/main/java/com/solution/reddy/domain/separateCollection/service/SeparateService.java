package com.solution.reddy.domain.separateCollection.service;

import com.solution.reddy.domain.category.entity.CategoryEntity;
import com.solution.reddy.domain.category.service.CategoryService;
import com.solution.reddy.domain.separateCollection.dto.SeparatePostRequest;
import com.solution.reddy.domain.separateCollection.dto.response.SeparatePostDetailDto;
import com.solution.reddy.domain.separateCollection.dto.response.SeparatePostResponseItem;
import com.solution.reddy.domain.separateCollection.dto.response.SeparatePostResponsePage;
import com.solution.reddy.domain.separateCollection.entity.SeparateEntity;
import com.solution.reddy.domain.separateCollection.repository.SeparateRepository;
import com.solution.reddy.global.dto.PageResponse;
import com.solution.reddy.global.exception.ApiException;
import com.solution.reddy.global.message.SeparateMessage;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeparateService {
    private final SeparateRepository separateRepository;
    private final CategoryService categoryService;

    public Long createSeparatePost(SeparatePostRequest requestDto) {
        CategoryEntity category = categoryService.findByCategoryName(requestDto.category());
        SeparateEntity entity = requestDto.toSeparateEntity(category);
        return separateRepository.save(entity).getId();
    }

    public SeparatePostResponsePage getSeparateByCategory(Long categoryId, Pageable pageable) {
        CategoryEntity category = categoryService.findByCategoryId(categoryId);
        Page<SeparatePostResponseItem> SeparatePostPage = separateRepository.findAllByCategory(category, pageable)
                .map(SeparateEntity::toSeparatePostResponseItem);

        return createSeparatePostResponsePageDto(SeparatePostPage);
    }

    @Transactional
    public SeparatePostResponsePage searchSeparatePost(String keyword, Pageable pageable) {
        Page<SeparatePostResponseItem> separatePostPage = separateRepository.searchSeparatePost(keyword, pageable)
                .map(SeparateEntity::toSeparatePostResponseItem);
        if(separatePostPage.isEmpty()) {
            return createSeparatePostResponsePageDto(null);
        }
        return createSeparatePostResponsePageDto(separatePostPage);
    }

    private SeparatePostResponsePage createSeparatePostResponsePageDto(Page<SeparatePostResponseItem> SeparatePostPage) {
        if(SeparatePostPage == null) {
            Page<SeparatePostResponseItem> emptyPage = Page.empty();
            PageResponse pageResponse = new PageResponse(emptyPage);
            return new SeparatePostResponsePage(List.of(), pageResponse);
        }

        if (SeparatePostPage.isEmpty()) {
            throw new ApiException(SeparateMessage.POST_NOT_FOUND);
        }

        PageResponse pageResponse = new PageResponse(SeparatePostPage);
        return new SeparatePostResponsePage(SeparatePostPage.toList(), pageResponse);
    }

    public SeparatePostDetailDto getSeparateById(Long postId) {
        return separateRepository.findById(postId)
                .map(SeparateEntity::toSeparatePostDetailDto)
                .orElseThrow(() -> new ApiException(SeparateMessage.POST_NOT_FOUND));
    }
}