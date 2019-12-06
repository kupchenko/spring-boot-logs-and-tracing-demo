package me.kupchenko.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kupchenko.client.ThirdPartyServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class TestController {

    private ThirdPartyServiceClient thirdPartyServiceClient;

    @GetMapping("/ok")
    public ResponseEntity<String> ok() {
        log.info("Some logging with zipkin traceId");
        return ResponseEntity.ok("test");
    }

    @GetMapping("/call-ok")
    public ResponseEntity<String> callOk() {
        log.info("Calling external service");
        thirdPartyServiceClient.getJobById();
        return ResponseEntity.ok("test");
    }

    @GetMapping("/error")
    public ResponseEntity<String> notok() {
        exception();
        return ResponseEntity.ok("test");
    }

    @GetMapping("/filter-test")
    public ResponseEntity<String> filterTest() {
        String email = "some@gmail.com";
        log.info("Trying to print the email: '{}'", email);
        return ResponseEntity.ok("email");
    }

    private void exception() {
        throw new RuntimeException("Some problems here!!!");
    }

}
