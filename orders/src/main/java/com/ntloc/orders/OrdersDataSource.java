package com.ntloc.orders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class OrdersDataSource {

    @Bean
    CommandLineRunner commandLineRunner(OrdersRepository ordersRepository) {
        return args -> {
            OrdersEntity orders = OrdersEntity.builder()
                    .customerId(1L)
                    .productId(1L)
                    .createAt(LocalDateTime.now()).build();
            ordersRepository.save(orders);
        };
    }
}
