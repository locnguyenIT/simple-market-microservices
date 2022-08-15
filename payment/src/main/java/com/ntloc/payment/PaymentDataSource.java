package com.ntloc.payment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class PaymentDataSource {

    @Bean
    CommandLineRunner commandLineRunner(PaymentRepository paymentRepository) {
        return args -> {
            PaymentEntity payment = PaymentEntity.builder()
                    .customerId(1L)
                    .ordersId(1L)
                    .createAt(LocalDateTime.now())
                    .build();
            paymentRepository.save(payment);
        };
    }
}
