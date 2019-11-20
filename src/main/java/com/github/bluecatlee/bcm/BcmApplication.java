package com.github.bluecatlee.bcm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.github.bluecatlee.bcm"})
public class BcmApplication {

    public static void main(String[] args) {
        SpringApplication.run(BcmApplication.class, args);
    }

}
