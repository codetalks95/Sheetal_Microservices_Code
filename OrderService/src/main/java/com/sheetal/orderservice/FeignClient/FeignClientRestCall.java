package com.sheetal.orderservice.FeignClient;

import com.sheetal.orderservice.Entity.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("PRODUCT-SERVICE")
public interface FeignClientRestCall {

    @GetMapping("/welcome")
    String welcomeToMicroservicesCode();

    @PostMapping("/message/{message}")
    String welcomeToMicroservice(@PathVariable String message);

    @PostMapping("/getEmployeeInformation")
    String getEmployeeInformation(@RequestBody Employee employee);
}