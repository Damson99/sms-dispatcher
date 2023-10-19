package com.smsdispatcher.infrastructure.persistence;

import com.smsdispatcher.domain.subscriber.NetworkMembershipEnum;
import com.smsdispatcher.domain.subscriber.NetworkSubscriberId;
import com.smsdispatcher.domain.subscriber.PhoneNumberValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class NetworkSubscriberEntity {

    @Id
    private NetworkSubscriberId id;
    @Indexed(unique = true)
    private PhoneNumberValue phoneNumberValue;
    private NetworkMembershipEnum networkMembership;
}
