package com.smsdispatcher.domain.subscriber;

public record NetworkSubscriber(NetworkSubscriberId id, PhoneNumberValue phoneNumber, NetworkMembershipEnum networkMembership) {

    public static NetworkSubscriber from(NetworkSubscriberId id, PhoneNumberValue phoneNumber) {
        return new NetworkSubscriber(id, phoneNumber, NetworkMembershipEnum.MEMBER);
    }

    public boolean isNotMember() {
        return networkMembership != NetworkMembershipEnum.MEMBER;
    }
}
