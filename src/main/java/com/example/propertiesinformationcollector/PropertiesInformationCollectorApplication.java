package com.example.propertiesinformationcollector;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class PropertiesInformationCollectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertiesInformationCollectorApplication.class, args);
    }
}
