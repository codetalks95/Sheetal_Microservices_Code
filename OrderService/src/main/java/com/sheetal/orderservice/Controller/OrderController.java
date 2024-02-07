package com.sheetal.orderservice.Controller;

import com.sheetal.orderservice.Entity.Employee;
import com.sheetal.orderservice.FeignClient.FeignClientRestCall;
import com.sheetal.orderservice.RestTemplate.RestTemplateConfiguration;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
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
        System.out.println("API GATEWAY IS CALLING ME:-");
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
    @CircuitBreaker(name = "getEmployeeTolerant", fallbackMethod = "faultTolerance")
    public String getEmployeeInfoFromRestTemplateFaultTolerance(@RequestBody Employee employee) {
        return restTemplateConfiguration.getEmployeeInformation(employee);
    }

    public String faultTolerance(Throwable throwable) {
        return "Hey Dude" + " " + "the service is down please come back Later or Give it a Retry!!";
    }

    @PostMapping("/getEmployeeInfoFromRestTemplateFaultToleranceLimit")
    @RateLimiter(name = "getMessageRateLimit", fallbackMethod = "faultTolerance")
    public String getEmployeeInfoFromRestTemplateFaultToleranceLimit(@RequestBody Employee employee) {
        return restTemplateConfiguration.getEmployeeInformation(employee);
    }

    @PostMapping("/getEmployeeInfoFromRestTemplateFaultToleranceRetry")
    @Retry(name = "getEmployeeRetry", fallbackMethod = "faultTolerance")
    public String getEmployeeInfoFromRestTemplateFaultToleranceRetry(@RequestBody Employee employee) {
        return restTemplateConfiguration.getEmployeeInformation(employee);
    }
}
