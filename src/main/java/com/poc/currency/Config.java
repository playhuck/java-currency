package com.poc.currency;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class Config {
    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplateBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();

    }
}
