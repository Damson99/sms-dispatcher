package com.smsdispatcher.infrastructure.persistence;

import com.smsdispatcher.domain.subscriber.NetworkSubscriber;

class NetworkSubscriberMapper {


    public NetworkSubscriberEntity map(NetworkSubscriber networkSubscriber) {
        return new NetworkSubscriberEntity(
                networkSubscriber.id(),
                networkSubscriber.phoneNumber(),
                networkSubscriber.networkMembership()
        );
    }

    public NetworkSubscriber map(NetworkSubscriberEntity subscriberEntity) {
        return new NetworkSubscriber(
                subscriberEntity.getId(),
                subscriberEntity.getPhoneNumberValue(),
                subscriberEntity.getNetworkMembership()
        );
    }
}
