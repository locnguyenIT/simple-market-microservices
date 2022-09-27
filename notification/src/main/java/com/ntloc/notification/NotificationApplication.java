package com.ntloc.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@EnableEurekaClient
//ScanBasePackages is scan package to fetch all class from that package
@SpringBootApplication(
        scanBasePackages = {
                "com.ntloc.notification",
                "com.ntloc.amqp",
        }
)
//PropertySources to use properties file to run different environments (local, docker, ...)
@PropertySources({
        @PropertySource("classpath:amqp-${spring.profiles.active}.properties")
})
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

}
