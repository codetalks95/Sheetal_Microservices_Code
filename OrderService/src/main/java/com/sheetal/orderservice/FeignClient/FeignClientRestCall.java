package com.sheetal.orderservice.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("PRODUCT-SERVICE")
public interface FeignClientRestCall {

    @GetMapping("/welcome")
    String welcomeToMicroservicesCode();

    @PostMapping("/message/{message}")
    String welcomeToMicroservice(@PathVariable String message);
}