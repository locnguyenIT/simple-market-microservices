package com.ntloc.payment;

import com.ntloc.payment.request.NotificationRequest;
import com.ntloc.payment.request.PaymentRequest;
import com.ntloc.payment.response.OrdersResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static com.ntloc.payment.PaymentConstant.PAYMENT_NOT_FOUND;

@AllArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final RestTemplate restTemplate;

    public List<PaymentDTO> getAllPayment() {
        List<PaymentEntity> allOrders = paymentRepository.findAll();
        return paymentMapper.toListDTO(allOrders);
    }

    public PaymentDTO getPayment(Long id) {
        PaymentEntity payment = paymentRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(PAYMENT_NOT_FOUND));
        return paymentMapper.toDTO(payment);
    }

    public PaymentDTO payment(PaymentRequest paymentRequest) {

        OrdersResponse ordersResponse = restTemplate.getForObject("http://localhost:8030/api/v1/orders/{id}",
                OrdersResponse.class,
                paymentRequest.getOrdersId());
        //Todo: Handle payment process
        PaymentEntity payment = paymentRepository.save(PaymentEntity.builder()
                .customerId(paymentRequest.getCustomerId())
                .ordersId(paymentRequest.getOrdersId())
                .createAt(LocalDateTime.now()).build());

        //2. Create notificationRequest
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .toCustomerId(paymentRequest.getCustomerId())
                .toCustomerName(paymentRequest.getCustomerName())
                .toCustomerEmail(paymentRequest.getCustomerEmail())
                .message(String.format("Hi %s. Your payment has been success. Thank you for visiting our PJ-AT team", paymentRequest.getCustomerName()))
                .build();

        restTemplate.postForObject("http://localhost:8050/api/v1/notification",
                notificationRequest,Void.class);

        return paymentMapper.toDTO(payment);
    }
}
