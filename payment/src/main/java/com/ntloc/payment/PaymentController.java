package com.ntloc.payment;

import com.ntloc.client.payment.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ntloc.payment.PaymentConstant.URI_REST_API_VERSION_PAYMENT;

@Slf4j
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
        log.info("PaymentId {}", id);
        return paymentService.getPayment(id);
    }

    @PostMapping
    public PaymentDTO payment(@RequestBody PaymentRequest paymentRequest) {
        log.info("Customer payment {}", paymentRequest);
        return paymentService.payment(paymentRequest);
    }
}
