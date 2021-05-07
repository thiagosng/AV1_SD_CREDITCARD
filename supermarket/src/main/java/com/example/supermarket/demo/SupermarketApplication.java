package com.example.supermarket.demo;

import com.example.supermarket.demo.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SupermarketApplication implements CommandLineRunner {

    @Autowired
    private SupermarketService supermarketService;

    public static void main(String[] args) {
        SpringApplication.run(SupermarketApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        supermarketService.findCreditCardById();
    }
}
