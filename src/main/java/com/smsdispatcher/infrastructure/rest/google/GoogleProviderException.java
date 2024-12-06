package com.smsdispatcher.infrastructure.rest.google;

public class GoogleProviderException extends RuntimeException {

    public GoogleProviderException(String msg) {
        super(msg, null, false, false);
    }
}
