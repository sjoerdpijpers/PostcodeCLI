package com.example.postcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PostcodeApplication {

    public static void main(String[] args) {
     SpringApplication.run(PostcodeApplication.class, args);
    }

}
