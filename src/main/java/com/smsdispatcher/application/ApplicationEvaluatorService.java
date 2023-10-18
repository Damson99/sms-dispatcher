package com.smsdispatcher.application;

import com.smsdispatcher.domain.dispatcher.DomainService;
import com.smsdispatcher.domain.dispatcher.EvaluatedContent;
import com.smsdispatcher.domain.subscriber.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ApplicationEvaluatorService {
    private final DomainService domainService;
    private final NetworkSubscriberRepository networkSubscriberRepository;

    public EvaluatedContent handle(EvaluateSMSContentCommand command) {
        final String recipient = command.recipient();
        final PhoneNumberValue phoneNumberValue = new PhoneNumberValue(recipient);
        log.info("looking for subscriber with phone number {}, recipient {}", recipient, recipient);
        final Optional<NetworkSubscriber> optionalNetworkSubscriber = networkSubscriberRepository.findByPhoneNumber(phoneNumberValue);
        if (optionalNetworkSubscriber.isEmpty()) {
            final NetworkSubscriberId newId = networkSubscriberRepository.nextIdentity();
            final NetworkSubscriber networkSubscriber = saveNewSubscriber(NetworkSubscriber.from(newId, phoneNumberValue));
            return domainService.evaluateContentFor(command.message(), networkSubscriber);
        }
        return domainService.evaluateContentFor(command.message(), optionalNetworkSubscriber.get());
    }

    public void handle(ChangeMembershipCommand command) {
        final String phoneNumber = command.subscriberPhoneNumber();
        final MembershipCommandEnum membershipCommandEnum = command.membershipCommandEnum();
        log.info("changing membership for {} to {}", phoneNumber, membershipCommandEnum);

        final PhoneNumberValue phoneNumberValue = new PhoneNumberValue(phoneNumber);
        final Optional<NetworkSubscriber> optionalNetworkSubscriber = networkSubscriberRepository.findByPhoneNumber(phoneNumberValue);
        if (optionalNetworkSubscriber.isEmpty()) {
            final NetworkSubscriberId newId = networkSubscriberRepository.nextIdentity();
            saveNewSubscriber(new NetworkSubscriber(newId, phoneNumberValue, NetworkMembershipEnum.from(membershipCommandEnum)));
        }
    }

    private NetworkSubscriber saveNewSubscriber(NetworkSubscriber newNetworkSubscriber) {
        log.info("saving new subscriber: id {}, phone number {}", newNetworkSubscriber.id(), newNetworkSubscriber.phoneNumber());
        return networkSubscriberRepository.save(newNetworkSubscriber);
    }
}
