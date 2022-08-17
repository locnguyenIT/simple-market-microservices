package com.ntloc.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//basePackages is scan package to fetch all Feign Client from that package
@EnableFeignClients(
        basePackages = "com.ntloc.client"
)
@EnableEurekaClient
//ScanBasePackages is scan package to fetch all class from that package
@SpringBootApplication(
        scanBasePackages = {
                "com.ntloc.payment",
                "com.ntloc.amqp"
        }
)
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }
}
