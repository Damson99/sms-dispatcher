package com.smsdispatcher.infrastructure.rest.google;

import com.smsdispatcher.domain.dispatcher.ContentEvaluatorProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class RestConfig {
    @Value("${web-client.googleapis-web-risk-base}")
    private String baseGoogleUrl;
    @Value("${web-client.googleapis-web-risk-endpoint}")
    private String googleUrlEndpoint;

    @Bean
    WebClient googleWebClient() {
        return WebClient.builder()
                .baseUrl(baseGoogleUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    ContentEvaluatorProvider googleContentEvaluatorProvider(WebClient googleWebClient) {
        return new GoogleContentEvaluatorProvider(googleWebClient, googleUrlEndpoint);
    }
}
