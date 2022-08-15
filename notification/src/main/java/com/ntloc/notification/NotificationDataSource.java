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
                    .sender("ntloc")
                    .message("Welcome to PJ-AT microservices")
                    .sentAt(LocalDateTime.now())
                    .build();

            notificationRepository.save(notification);
        };
    }
}
