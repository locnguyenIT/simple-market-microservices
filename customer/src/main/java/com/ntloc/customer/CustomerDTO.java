package com.ntloc.customer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDTO {

    private Long id;
    private String name;
    private String email;
}
