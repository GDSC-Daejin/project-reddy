package com.solution.reddy.domain.result.dto;

import com.solution.reddy.global.api.dto.AirequestDto;
import lombok.Builder;

@Builder
public record AIResultRequest(
        String imageUrl
) {
    public AirequestDto toAirequestDto() {
        return new AirequestDto(this.imageUrl());
    }
}
