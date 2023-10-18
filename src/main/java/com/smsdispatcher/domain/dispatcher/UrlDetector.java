package com.smsdispatcher.domain.dispatcher;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class UrlDetector {
    private static final String URL_REGEX = "https?:[\\S]{1,100}";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE);

    public List<String> detectUrls(String message, int maxUrlsInMessage) {
        final List<String> urlsFromMessage = new LinkedList<>();
        final Matcher urlMatcher = URL_PATTERN.matcher(message);
        while (urlMatcher.find() && urlsFromMessage.size() != maxUrlsInMessage) {
            urlsFromMessage.add(message.substring(urlMatcher.start(0), urlMatcher.end(0)));
        }
        return urlsFromMessage;
    }
}
