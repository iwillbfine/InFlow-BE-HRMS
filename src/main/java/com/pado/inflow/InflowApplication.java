package com.pado.inflow;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@EnableScheduling
@SpringBootApplication
@Slf4j
public class InflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(InflowApplication.class, args);
    }

    @PostConstruct
    public void init() {
        // JVM 전역 시간대 설정
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        log.info("현재 시스템 시간대: {}", TimeZone.getDefault().getID());
    }

}
