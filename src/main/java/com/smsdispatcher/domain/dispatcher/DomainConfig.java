package com.smsdispatcher.domain.dispatcher;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class DomainConfig {

    @Bean
    UrlDetector urlDetector() {
        return new UrlDetector();
    }

    @Bean
    DomainService domainService(ContentEvaluatorProvider contentEvaluatorProvider, UrlDetector urlDetector) {
        return new DispatchDomainService(contentEvaluatorProvider, urlDetector);
    }
}
