package com.ntloc.client.payment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentRequest {

    private Long customerId;
    private String customerName;
    private String customerEmail;
    private Long ordersId;
}
