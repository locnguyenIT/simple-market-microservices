package com.ntloc.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductDataSource {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            ProductEntity chalua = ProductEntity.builder()
                    .name("Cha lua")
                    .price(3000).build();
            ProductEntity nemchua = ProductEntity.builder()
                    .name("Nem chua")
                    .price(50000).build();
            productRepository.saveAll(List.of(chalua, nemchua));
        };
    }
}
