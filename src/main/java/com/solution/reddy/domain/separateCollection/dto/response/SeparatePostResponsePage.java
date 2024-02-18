package com.solution.reddy.domain.separateCollection.dto.response;

import com.solution.reddy.global.dto.PageResponse;
import java.util.List;

public record SeparatePostResponsePage(
        List<SeparatePostResponseItem> separatePosts,
        PageResponse page
) {
}
