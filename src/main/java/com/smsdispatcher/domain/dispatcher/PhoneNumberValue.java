package com.smsdispatcher.domain.dispatcher;

public record PhoneNumberValue(String phoneNumber) {

    public String areaCode() {
        return phoneNumber.substring(0, 2);
    }

    public String phoneNumber() {
        return phoneNumber.substring(2);
    }
}
