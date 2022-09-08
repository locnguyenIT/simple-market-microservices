package com.ntloc.orders;

import com.ntloc.amqp.RabbitMQProducer;
import com.ntloc.client.notification.NotificationRequest;
import com.ntloc.client.orders.OrdersRequest;
import com.ntloc.client.product.ProductClient;
import com.ntloc.client.product.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.ntloc.orders.OrdersConstant.ORDERS_NOT_FOUND;

@AllArgsConstructor
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;
    private final ProductClient productClient;
    private final RabbitMQProducer rabbitMQProducer;

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
        //1. Find product
        ProductResponse product = productClient.getProduct(ordersRequest.getProductId());

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
                .subject("Orders process")
                .message("Your orders has been successfully.")
                .build();

        //3. Send notification to notification.queue
        rabbitMQProducer.publish("internal.exchange", "internal.notification.routing-key", notificationRequest);
        //4. Map & return
        return ordersMapper.toDTO(orders);

    }
}
