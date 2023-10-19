package com.smsdispatcher.infrastructure.persistence;

import com.smsdispatcher.domain.subscriber.NetworkSubscriber;
import com.smsdispatcher.domain.subscriber.PhoneNumberValue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface MongoNetworkSubscriberRepository extends MongoRepository<NetworkSubscriberEntity, String> {

    Optional<NetworkSubscriber> findByPhoneNumberValue(PhoneNumberValue byPhoneNumberValue);
}
