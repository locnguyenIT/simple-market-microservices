package com.ntloc.client.payment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PaymentResponse {

    private Long id;
    private Long customerId;
    private Long ordersId;
    private LocalDateTime createAt;
}
