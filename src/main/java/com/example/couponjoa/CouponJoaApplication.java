package com.example.couponjoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CouponJoaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponJoaApplication.class, args);
    }

}
