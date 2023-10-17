package com.smsdispatcher.application;

public class NetworkSubscriberNotFound extends RuntimeException {
    public static final NetworkSubscriberNotFound NOT_FOUND = initException();

    private static NetworkSubscriberNotFound initException() {
        return new NetworkSubscriberNotFound();
    }

    private NetworkSubscriberNotFound() {
        super("network subscriber not found", null, false, false);
    }
}
