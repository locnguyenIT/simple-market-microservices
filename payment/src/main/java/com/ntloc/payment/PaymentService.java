package com.ntloc.payment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ntloc.payment.PaymentConstant.PAYMENT_NOT_FOUND;

@AllArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public List<PaymentDTO> getAllPayment() {
        List<PaymentEntity> allOrders = paymentRepository.findAll();
        return paymentMapper.toListDTO(allOrders);
    }

    public PaymentDTO getPayment(Long id) {
        PaymentEntity payment = paymentRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(PAYMENT_NOT_FOUND));
        return paymentMapper.toDTO(payment);
    }
}
