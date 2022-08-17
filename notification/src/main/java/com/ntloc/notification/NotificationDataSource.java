package com.ntloc.notification;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class NotificationDataSource {

    @Bean
    CommandLineRunner commandLineRunner(NotificationRepository notificationRepository) {
        return args -> {
            NotificationEntity notification = NotificationEntity.builder()
                    .toCustomerId(1L)
                    .toCustomerName("Nguyen Thanh Loc")
                    .toCustomerEmail("ntloc.developer@gmail.com")
                    .subject("Microservices")
                    .sender("ntloc")
                    .message("Welcome to PJ-AT")
                    .sentAt(LocalDateTime.now())
                    .build();

            notificationRepository.save(notification);
        };
    }
}
