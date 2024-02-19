package com.solution.reddy.global.api;

import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "reddy", url = "${feign.svc1.name.url}",configuration = AiModelApi.FeignConfig.class)
public interface AiModelApi {

    @PostMapping(value = "/api/v1/ai-models", produces = MediaType.APPLICATION_JSON_VALUE)
    void runAiModel(
            @RequestBody String imageUrl
    );

    class FeignConfig {
        @Bean
        ErrorDecoder errorDecoder() {
            return new SearchErrorDecoder();
        }
    }
}
