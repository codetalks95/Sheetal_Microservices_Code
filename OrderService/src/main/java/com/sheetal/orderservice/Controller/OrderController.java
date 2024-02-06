package com.sheetal.orderservice.Controller;

import com.sheetal.orderservice.Entity.Employee;
import com.sheetal.orderservice.FeignClient.FeignClientRestCall;
import com.sheetal.orderservice.RestTemplate.RestTemplateConfiguration;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    FeignClientRestCall feignClientRestCall;
    RestTemplateConfiguration restTemplateConfiguration;

    @Autowired
    public OrderController(FeignClientRestCall feignClientRestCall, RestTemplateConfiguration restTemplateConfiguration) {
        this.feignClientRestCall = feignClientRestCall;
        this.restTemplateConfiguration = restTemplateConfiguration;
    }

    @GetMapping("/getMessage")
    public String getMessage() {
        return feignClientRestCall.welcomeToMicroservicesCode();
    }

    @PostMapping("/message/{message}")
    public String getMessage(@PathVariable String message) {
        return feignClientRestCall.welcomeToMicroservice(message);
    }

    @GetMapping("/getMessageFromRestTemplate")
    public String getMessageFromRestTemplate() {
        return restTemplateConfiguration.welcomeToMicroservicesCode();
    }

    @PostMapping("/getEmployeeInfoFromFeignClient")
    public String getEmployeeInfoFromFeignClient(@RequestBody Employee employee) {
        return feignClientRestCall.getEmployeeInformation(employee);
    }

    @PostMapping("/getEmployeeInfoFromRestTemplate")
    public String getEmployeeInfoFromRestTemplate(@RequestBody Employee employee) {
        return restTemplateConfiguration.getEmployeeInformation(employee);
    }

    @PostMapping("/getEmployeeInfoFromRestTemplateFaultTolerance")
    @CircuitBreaker(name = "Fault Tolerance", fallbackMethod = "faultTolerance")
    public String getEmployeeInfoFromRestTemplateFaultTolerance(@RequestBody Employee employee) {
        throw new RuntimeException();
    }

    public String faultTolerance(Throwable throwable) {
        return "Hey Dude" + " " + "the service is down please come back Later or Give it a Retry!!";
    }
}
