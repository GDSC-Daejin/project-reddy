package com.solution.reddy.domain.article.dto.response;

import com.solution.reddy.global.dto.PageResponse;
import java.util.List;

public record ArticleTitleResponseDto (
        List<ArticleTitleItems> restaurants,
        PageResponse page
)
{ }
