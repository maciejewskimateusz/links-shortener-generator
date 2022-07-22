package com.example.linksshortenergenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LinksShortenerGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinksShortenerGeneratorApplication.class, args);
    }

}
