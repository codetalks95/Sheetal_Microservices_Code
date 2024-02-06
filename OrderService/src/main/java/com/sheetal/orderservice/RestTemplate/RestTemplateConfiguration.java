package com.sheetal.orderservice.RestTemplate;

import com.sheetal.orderservice.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestTemplateConfiguration {
    RestTemplate restTemplate;

    @Value("${productServiceGetURL}")
    private String productServiceGetURL;
    @Value("${employeeInformationPostUrl}")
    private String employeeInformationPostUrl;

    @Autowired
    public RestTemplateConfiguration(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String welcomeToMicroservicesCode() {
        String getUserURL = productServiceGetURL;
        ResponseEntity<String> responseEntity = restTemplate.exchange(getUserURL, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
        });
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            return null;
        }
    }

    public String getEmployeeInformation(Employee employee) {
        String postEmployeeInfoURL = employeeInformationPostUrl;
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", employee.getName());
        requestBody.put("age", employee.getAge());
        HttpEntity<Map<String, String>> mapHttpEntity = new HttpEntity<>(requestBody);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(postEmployeeInfoURL, mapHttpEntity, String.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody().toString();
        } else {
            return null;
        }
    }
}
