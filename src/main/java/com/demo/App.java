package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {
    private static ApplicationContext applicationContext;


    public static void main (String[] args) {
        SpringApplication.run(App.class, args);
    }
}
