package com.project.authorization_server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {
    @GetMapping("/")
    public String getMainPage() {
        log.info("Main Page");
        return "Hello";
    }

    @GetMapping("/init")
    public String getInitPage() {
        log.info("Init Page");
        return "Init";
    }
}
