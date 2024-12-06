package com.smsdispatcher.infrastructure.persistence;

import com.smsdispatcher.domain.subscriber.NetworkSubscriberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PersistenceConfig {

    @Bean
    NetworkSubscriberMapper networkSubscriberMapper() {
        return new NetworkSubscriberMapper();
    }

    @Bean
    NetworkSubscriberRepository networkSubscriberRepository(MongoNetworkSubscriberRepository mongoNetworkSubscriberRepository,
                                                            NetworkSubscriberMapper networkSubscriberMapper) {
        return new NetworkSubscriberRepositoryAdapter(mongoNetworkSubscriberRepository, networkSubscriberMapper);
    }
}
