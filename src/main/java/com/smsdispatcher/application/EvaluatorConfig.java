package com.smsdispatcher.application;

import com.smsdispatcher.domain.dispatcher.DomainService;
import com.smsdispatcher.domain.subscriber.NetworkSubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
class EvaluatorConfig {
    private final DomainService domainService;
    private final NetworkSubscriberRepository networkSubscriberRepository;

    @Bean
    ApplicationEvaluatorService applicationEvaluatorService() {
        return new ApplicationEvaluatorService(domainService, networkSubscriberRepository);
    }
}
