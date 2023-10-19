package com.smsdispatcher.domain.subscriber;

public enum NetworkMembershipEnum {
    MEMBER,
    NOT_MEMBER,
    UNKNOWN;

    public static NetworkMembershipEnum from(MembershipCommandEnum membershipCommandEnum) {
        if (membershipCommandEnum == MembershipCommandEnum.STOP) {
            return NetworkMembershipEnum.NOT_MEMBER;
        } else if (membershipCommandEnum == MembershipCommandEnum.START) {
            return NetworkMembershipEnum.MEMBER;
        } else {
            return NetworkMembershipEnum.UNKNOWN;
        }
    }
}
