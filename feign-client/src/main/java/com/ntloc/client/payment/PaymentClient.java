package com.ntloc.client.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment")
public interface PaymentClient {

    @PostMapping(path = "/api/v1/payment")
    PaymentResponse payment(@RequestBody PaymentRequest paymentRequest);
}
