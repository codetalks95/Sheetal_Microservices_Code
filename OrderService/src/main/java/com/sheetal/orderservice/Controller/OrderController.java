package com.sheetal.orderservice.Controller;

import com.sheetal.orderservice.FeignClient.FeignClientRestCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    FeignClientRestCall feignClientRestCall;

    @Autowired
    public OrderController(FeignClientRestCall feignClientRestCall) {
        this.feignClientRestCall = feignClientRestCall;
    }

    @GetMapping("/getMessage")
    public String getMessage() {
        return feignClientRestCall.welcomeToMicroservicesCode();
    }

    @PostMapping("/message/{message}")
    public String getMessage(@PathVariable String message) {
        return feignClientRestCall.welcomeToMicroservice(message);
    }
}
