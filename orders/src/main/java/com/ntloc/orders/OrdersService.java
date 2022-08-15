package com.ntloc.orders;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ntloc.orders.OrdersConstant.ORDERS_NOT_FOUND;

@AllArgsConstructor
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;

    public List<OrdersDTO> getAllOrders() {
        List<OrdersEntity> allOrders = ordersRepository.findAll();
        return ordersMapper.toListDTO(allOrders);
    }

    public OrdersDTO getOrders(Long id) {
        OrdersEntity orders = ordersRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(ORDERS_NOT_FOUND));
        return ordersMapper.toDTO(orders);
    }


}
