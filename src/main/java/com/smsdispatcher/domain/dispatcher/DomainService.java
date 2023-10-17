package com.smsdispatcher.domain.dispatcher;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DomainService {
    private static final String URL_REGEX = "https?:(//|)[\\S]{1,100}";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE);
    protected static final int MAX_URLS_IN_MESSAGE = 5;

    public abstract EvaluatedContent evaluateContentFor(String message, NetworkSubscriber networkSubscriber);

    protected List<String> getUrlsFromMessage(String message) {
        final List<String> urlsFromMessage = new LinkedList<>();
        final Matcher urlMatcher = URL_PATTERN.matcher(message);
        while (urlMatcher.find() && urlsFromMessage.size() != MAX_URLS_IN_MESSAGE) {
            urlsFromMessage.add(message.substring(urlMatcher.start(0), urlMatcher.end(0)));
        }
        return urlsFromMessage;
    }
}
