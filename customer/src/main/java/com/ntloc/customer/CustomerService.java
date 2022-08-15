package com.ntloc.customer;

import com.ntloc.customer.request.OrdersRequest;
import com.ntloc.customer.request.PaymentRequest;
import com.ntloc.customer.response.OrdersResponse;
import com.ntloc.customer.response.PaymentResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.ntloc.customer.CustomerConstant.CUSTOMER_NOT_FOUND;

@Slf4j
@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final RestTemplate restTemplate;

    public List<CustomerDTO> getAllCustomer() {
        return customerMapper.toListDTO(customerRepository.findAll());

    }

    public OrdersResponse orders(OrdersRequest ordersRequest) {
        CustomerEntity customer = customerRepository.findById(ordersRequest.getCustomerId()).orElseThrow(() ->
                new IllegalStateException(CUSTOMER_NOT_FOUND));

        OrdersResponse ordersResponse = restTemplate.postForObject("http://localhost:8030/api/v1/orders",
                ordersRequest, OrdersResponse.class);

        return ordersResponse;
    }

    public PaymentResponse payment(PaymentRequest paymentRequest) {
        CustomerEntity customer = customerRepository.findById(paymentRequest.getCustomerId()).orElseThrow(() ->
                new IllegalStateException(CUSTOMER_NOT_FOUND));

        PaymentResponse paymentResponse = restTemplate.postForObject("http://localhost:8040/api/v1/payment",
                paymentRequest, PaymentResponse.class);

        return paymentResponse;
    }
}
