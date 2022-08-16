package com.ntloc.payment;

import com.ntloc.client.notification.NotificationClient;
import com.ntloc.client.notification.NotificationRequest;
import com.ntloc.client.orders.OrdersClient;
import com.ntloc.client.orders.OrdersResponse;
import com.ntloc.client.payment.PaymentRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.ntloc.payment.PaymentConstant.PAYMENT_NOT_FOUND;

@AllArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrdersClient ordersClient;
    private final NotificationClient notificationClient;

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

        OrdersResponse orders = ordersClient.getOrders(paymentRequest.getOrdersId());

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

        notificationClient.sendNotification(notificationRequest);

        return paymentMapper.toDTO(payment);
    }
}
