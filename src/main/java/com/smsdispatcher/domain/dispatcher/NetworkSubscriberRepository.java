package com.smsdispatcher.domain.dispatcher;

import java.util.Optional;

public interface NetworkSubscriberRepository {

    Optional<NetworkSubscriber> findByPhoneNumber(PhoneNumberValue phoneNumber);
}
