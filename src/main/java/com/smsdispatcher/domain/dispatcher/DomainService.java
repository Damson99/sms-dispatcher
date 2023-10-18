package com.smsdispatcher.domain.dispatcher;

import com.smsdispatcher.domain.subscriber.NetworkSubscriber;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class DomainService {
    protected static final int MAX_URLS_IN_MESSAGE = 5;
    protected final UrlDetector urlDetector;

    public abstract EvaluatedContent evaluateContentFor(String message, NetworkSubscriber networkSubscriber);

    protected List<String> getUrlsFromMessage(String message) {
        return urlDetector.detectUrls(message, MAX_URLS_IN_MESSAGE);
    }
}
