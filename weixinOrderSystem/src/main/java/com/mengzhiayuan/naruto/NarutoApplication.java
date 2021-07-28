package com.mengzhiayuan.naruto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NarutoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NarutoApplication.class, args);
    }

}
