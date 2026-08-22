package com.player32611;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TakeOutApplication {

    static void main(String[] args) {
        SpringApplication.run(TakeOutApplication.class, args);
    }

}