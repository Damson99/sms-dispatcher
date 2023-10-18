package com.smsdispatcher.infrastructure.rest.google;

public class GoogleProviderException extends RuntimeException {

    public GoogleProviderException() {
        super("", null, false, false);
    }
}
