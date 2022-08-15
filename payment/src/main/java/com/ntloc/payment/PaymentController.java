package com.ntloc.payment;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ntloc.payment.PaymentConstant.URI_REST_API_VERSION_PAYMENT;

@AllArgsConstructor
@RestController
@RequestMapping(path = URI_REST_API_VERSION_PAYMENT)
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public List<PaymentDTO> getAllPayment() {
        return paymentService.getAllPayment();
    }

    @GetMapping(path = "/{id}")
    public PaymentDTO getPayment(@PathVariable("id") Long id) {
        return paymentService.getPayment(id);
    }
}
