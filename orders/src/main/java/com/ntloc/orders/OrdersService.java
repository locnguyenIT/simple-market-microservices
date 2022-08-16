package com.ntloc.orders;

import com.ntloc.orders.request.NotificationRequest;
import com.ntloc.orders.request.OrdersRequest;
import com.ntloc.orders.response.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static com.ntloc.orders.OrdersConstant.ORDERS_NOT_FOUND;

@AllArgsConstructor
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;
    private final RestTemplate restTemplate;

    public List<OrdersDTO> getAllOrders() {
        List<OrdersEntity> allOrders = ordersRepository.findAll();
        return ordersMapper.toListDTO(allOrders);
    }

    public OrdersDTO getOrders(Long id) {
        OrdersEntity orders = ordersRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(ORDERS_NOT_FOUND));
        return ordersMapper.toDTO(orders);
    }


    public OrdersDTO order(OrdersRequest ordersRequest) {

        ProductResponse productResponse = restTemplate.getForObject("http://localhost:8020/api/v1/product/{id}",
                ProductResponse.class,
                ordersRequest.getProductId());

//        ProductResponse productResponse = restTemplate.getForObject("http://PRODUCT/api/v1/product/{id}",
//                ProductResponse.class,
//                ordersRequest.getProductId());

        //Todo: Handle orders process
        OrdersEntity orders = ordersRepository.save(OrdersEntity.builder()
                .customerId(ordersRequest.getCustomerId())
                .productId(ordersRequest.getProductId())
                .createAt(LocalDateTime.now()).build());

        //2. Create notificationRequest
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .toCustomerId(ordersRequest.getCustomerId())
                .toCustomerName(ordersRequest.getCustomerName())
                .toCustomerEmail(ordersRequest.getCustomerEmail())
                .message(String.format("Hi %s. Your orders has been success.", ordersRequest.getCustomerName()))
                .build();

        restTemplate.postForObject("http://localhost:8050/api/v1/notification",
                notificationRequest,Void.class);

//        restTemplate.postForObject("http://NOTIFICATION/api/v1/notification",
//                notificationRequest,Void.class);

        return ordersMapper.toDTO(orders);

    }
}
