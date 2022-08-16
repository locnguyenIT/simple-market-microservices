package com.ntloc.client.orders;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrdersRequest {

    private Long customerId;
    private String customerName;
    private String customerEmail;
    private Long productId;
}
