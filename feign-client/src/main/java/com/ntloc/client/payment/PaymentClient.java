package com.ntloc.client.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment", url = "${client.payment.url}")
public interface PaymentClient {

    @PostMapping(path = "/payment/api/v1/payment")
    PaymentResponse payment(@RequestBody PaymentRequest paymentRequest);
}
