package com.smsdispatcher.domain.subscriber;

import java.util.Optional;

public interface NetworkSubscriberRepository {

    Optional<NetworkSubscriber> findByPhoneNumber(PhoneNumberValue phoneNumber);

    NetworkSubscriber save(NetworkSubscriber newNetworkSubscriber);

    NetworkSubscriberId nextIdentity();
}
