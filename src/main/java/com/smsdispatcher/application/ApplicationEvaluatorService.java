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
        final String recipient = command.recipient();
        final PhoneNumberValue phoneNumberValue = new PhoneNumberValue(recipient);
        log.info("looking for subscriber with phone number {}, recipient {}", recipient, recipient);
        final Optional<NetworkSubscriber> optionalNetworkSubscriber = networkSubscriberRepository.findByPhoneNumber(phoneNumberValue);
        final NetworkSubscriber networkSubscriber = optionalNetworkSubscriber.orElseThrow(() -> {
            log.warn("subscriber not found {}", recipient);
            throw NetworkSubscriberNotFound.NOT_FOUND;
        });
        return domainService.evaluateContentFor(command.message(), networkSubscriber);
    }
}
