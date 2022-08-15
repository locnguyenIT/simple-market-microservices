package com.ntloc.payment.request;

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
