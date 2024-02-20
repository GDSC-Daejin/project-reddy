package com.solution.reddy.global.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.solution.reddy")
public class OpenFeignConfig {
}
