package com.sheetal.productservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/welcome")
    public String welcomeToMicroservicesCode() {
        return "Welcome to Microservices";
    }

    @PostMapping("/message/{message}")
    public String welcomeToMicroservice(@PathVariable String message) {
        return "Welcome to" + " " + message;
    }
}
