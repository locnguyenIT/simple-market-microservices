package com.ntloc.notification;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NotificationRequest {

    private Long toCustomerId;
    private String toCustomerName;
    private String toCustomerEmail;
    private String message;
}
