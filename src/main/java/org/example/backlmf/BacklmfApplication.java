package org.example.backlmf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BacklmfApplication {

    public static void main(String[] args) {
        SpringApplication.run(BacklmfApplication.class, args);
    }

}
