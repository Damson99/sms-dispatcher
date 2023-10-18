package com.smsdispatcher.application;

import com.smsdispatcher.domain.subscriber.MembershipCommandEnum;

public record ChangeMembershipCommand(String subscriberPhoneNumber, MembershipCommandEnum membershipCommandEnum) {}
