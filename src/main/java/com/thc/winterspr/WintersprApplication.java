package com.thc.winterspr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WintersprApplication {
    public static void main(String[] args) {
        SpringApplication.run(WintersprApplication.class, args);
    }
}
