package com.solution.reddy.domain.result.dto;

import com.solution.reddy.domain.result.entity.ResultPostEntity;
import lombok.Builder;

@Builder
public record SaveCheckResultRequest(
        String imageUrl,
        Long resultId,
        Long groupId
) {
}
