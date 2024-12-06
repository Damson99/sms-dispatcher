package com.smsdispatcher.infrastructure.persistence;

import com.smsdispatcher.domain.subscriber.NetworkSubscriber;
import com.smsdispatcher.domain.subscriber.NetworkSubscriberId;
import com.smsdispatcher.domain.subscriber.NetworkSubscriberRepository;
import com.smsdispatcher.domain.subscriber.PhoneNumberValue;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class NetworkSubscriberRepositoryAdapter implements NetworkSubscriberRepository {
    private final MongoNetworkSubscriberRepository mongoNetworkSubscriberRepository;
    private final NetworkSubscriberMapper networkSubscriberMapper;

    @Override
    public Optional<NetworkSubscriber> findByPhoneNumber(PhoneNumberValue byPhoneNumberValue) {
        return mongoNetworkSubscriberRepository.findByPhoneNumberValue(byPhoneNumberValue);
    }

    @Override
    public NetworkSubscriber save(NetworkSubscriber newNetworkSubscriber) {
        final NetworkSubscriberEntity subscriberEntity = networkSubscriberMapper.map(newNetworkSubscriber);
        final NetworkSubscriberEntity savedSubscriberEntity = mongoNetworkSubscriberRepository.save(subscriberEntity);
        return networkSubscriberMapper.map(savedSubscriberEntity);
    }

    @Override
    public NetworkSubscriberId nextIdentity() {
        return new NetworkSubscriberId(UUID.randomUUID().toString());
    }
}
