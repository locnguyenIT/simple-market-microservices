package com.ntloc.payment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PaymentDTO {

    private Long id;
    private Long customerId;
    private Long ordersId;
    private Integer total;
    private LocalDateTime createAt;
}
