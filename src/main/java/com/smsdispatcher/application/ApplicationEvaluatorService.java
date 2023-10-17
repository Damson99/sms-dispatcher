package com.smsdispatcher.application;

import com.smsdispatcher.domain.dispatcher.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ApplicationEvaluatorService {
    private final DomainService domainService;
    private final NetworkSubscriberRepository networkSubscriberRepository;

    public EvaluatedContent handle(EvaluateSMSContentCommand command) {
        final PhoneNumberValue phoneNumberValue = new PhoneNumberValue(command.recipient());
        log.info("looking for subscriber with phone number {}, recipient {}", command.recipient(), command.recipient());
        final Optional<NetworkSubscriber> optionalNetworkSubscriber = networkSubscriberRepository.findByPhoneNumber(phoneNumberValue);
        final NetworkSubscriber networkSubscriber = optionalNetworkSubscriber.orElseThrow(() -> NetworkSubscriberNotFound.NOT_FOUND);
        if (networkSubscriber.isNotMember()) {
            return EvaluatedContent.NOT_MEMBER;
        }
        return domainService.evaluateContentFor(command.message(), networkSubscriber);
    }
}
