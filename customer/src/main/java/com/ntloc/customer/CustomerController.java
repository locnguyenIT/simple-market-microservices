package com.ntloc.customer;

import com.ntloc.client.orders.OrdersRequest;
import com.ntloc.client.orders.OrdersResponse;
import com.ntloc.client.payment.PaymentRequest;
import com.ntloc.client.payment.PaymentResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ntloc.customer.CustomerConstant.URI_REST_API_VERSION_CUSTOMER;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = URI_REST_API_VERSION_CUSTOMER)
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @PostMapping(path = "/orders")
    public OrdersResponse orders(@RequestBody OrdersRequest ordersRequest) {
        log.info("Customer orders {}", ordersRequest);
        return customerService.orders(ordersRequest);
    }

    @PostMapping(path = "/payment")
    public PaymentResponse payment(@RequestBody PaymentRequest paymentRequest) {
        log.info("Customer payment {}", paymentRequest);
        return customerService.payment(paymentRequest);
    }


}
