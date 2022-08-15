package com.ntloc.orders;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ntloc.orders.OrdersConstant.URI_REST_API_VERSION_ORDERS;

@AllArgsConstructor
@RestController
@RequestMapping(path = URI_REST_API_VERSION_ORDERS)
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping
    public List<OrdersDTO> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @GetMapping(path = "/{id}")
    public OrdersDTO getOrders(@PathVariable("id") Long id) {
        return ordersService.getOrders(id);
    }

}
