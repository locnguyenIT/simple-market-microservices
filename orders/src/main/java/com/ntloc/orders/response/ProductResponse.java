package com.ntloc.orders.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductResponse {

    private Long id;
    private String name;
    private Integer price;
}
