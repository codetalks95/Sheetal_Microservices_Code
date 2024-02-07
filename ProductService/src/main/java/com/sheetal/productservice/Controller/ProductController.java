package com.sheetal.productservice.Controller;

import com.sheetal.productservice.Entity.Employee;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/welcome")
    public String welcomeToMicroservicesCode() {
        return "Welcome to Microservices";
    }

    @PostMapping("/message/{message}")
    public String welcomeToMicroservice(@PathVariable String message) {
        return "Welcome to" + " " + message;
    }

    @PostMapping("/getEmployeeInformation")
    public String getEmployeeInformation(@RequestBody Employee employee) {
        return "Welcome" + " " + employee.getName() + " " + "having age less then" + " " + employee.getAge() + ".";
    }
}
