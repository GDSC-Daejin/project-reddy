package com.solution.reddy.global.api.dto;

import java.util.List;

public record AiModelResponse(
        PredictImgResult predictImgResult,
        List<Double> score
) { }
