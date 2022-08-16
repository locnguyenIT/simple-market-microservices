package com.ntloc.client.orders;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrdersResponse {

    private Long id;
    private Long customerId;
    private Long productId;
    private LocalDateTime createAt;
}
