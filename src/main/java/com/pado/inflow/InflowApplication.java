package com.pado.inflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class InflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(InflowApplication.class, args);
    }

}
