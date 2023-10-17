package com.smsdispatcher.domain.dispatcher;

public record NetworkSubscriber(String phoneNumber, NetworkMembershipEnum networkMembership) {

    public boolean isNotMember() {
        return networkMembership != NetworkMembershipEnum.MEMBER;
    }
}
