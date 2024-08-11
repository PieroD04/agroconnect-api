package com.agroconnect.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AgroconnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgroconnectApplication.class, args);
    }

}
