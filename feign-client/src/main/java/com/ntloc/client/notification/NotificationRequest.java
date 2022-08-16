package com.ntloc.client.notification;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequest {

    private Long toCustomerId;
    private String toCustomerName;
    private String toCustomerEmail;
    private String message;
}
