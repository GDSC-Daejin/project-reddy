package com.solution.reddy.global.api;

import com.solution.reddy.global.api.dto.AiModelResponse;
import com.solution.reddy.global.api.dto.AirequestDto;
import com.solution.reddy.global.exception.RunModelErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "reddy", url = "${feign.svc1.name.url}",configuration = AiModelApi.FeignConfig.class)
public interface AiModelApi {

    @PostMapping(value = "/predict", produces = MediaType.APPLICATION_JSON_VALUE)
    AiModelResponse runAiModel(
            @RequestBody AirequestDto imageUrl
    );

    class FeignConfig {
        @Bean
        ErrorDecoder errorDecoder() {
            return new RunModelErrorDecoder();
        }
    }
}
