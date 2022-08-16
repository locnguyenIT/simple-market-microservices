package com.ntloc.customer;


import com.ntloc.client.orders.OrdersClient;
import com.ntloc.client.orders.OrdersRequest;
import com.ntloc.client.orders.OrdersResponse;
import com.ntloc.client.payment.PaymentClient;
import com.ntloc.client.payment.PaymentRequest;
import com.ntloc.client.payment.PaymentResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ntloc.customer.CustomerConstant.CUSTOMER_NOT_FOUND;

@Slf4j
@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final OrdersClient ordersClient;
    private final PaymentClient paymentClient;

    public List<CustomerDTO> getAllCustomer() {
        return customerMapper.toListDTO(customerRepository.findAll());

    }

    public OrdersResponse orders(OrdersRequest ordersRequest) {
        CustomerEntity customer = customerRepository.findById(ordersRequest.getCustomerId()).orElseThrow(() ->
                new IllegalStateException(CUSTOMER_NOT_FOUND));

        OrdersResponse order = ordersClient.order(ordersRequest);

        return order;
    }

    public PaymentResponse payment(PaymentRequest paymentRequest) {
        CustomerEntity customer = customerRepository.findById(paymentRequest.getCustomerId()).orElseThrow(() ->
                new IllegalStateException(CUSTOMER_NOT_FOUND));

        PaymentResponse payment = paymentClient.payment(paymentRequest);

        return payment;
    }
}
