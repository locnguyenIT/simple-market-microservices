package com.ntloc.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerDataSource {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            CustomerEntity customer = CustomerEntity.builder()
                    .name("Nguyen Thanh Loc")
                    .email("ntloc.developer@gmail.com").build();
            customerRepository.save(customer);
        };
    }
}
