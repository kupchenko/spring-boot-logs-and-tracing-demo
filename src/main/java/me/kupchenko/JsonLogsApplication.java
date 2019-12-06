package me.kupchenko;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;

@Slf4j
@EnableFeignClients
@SpringBootApplication
public class JsonLogsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsonLogsApplication.class, args);
    }

    @PostConstruct
    public void setUp() {
        log.trace("test");
        log.info("test");
        log.debug("test");
        log.warn("test");
        log.error("test");
    }


}
