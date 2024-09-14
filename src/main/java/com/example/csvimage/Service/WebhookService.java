package com.example.csvimage.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.example.csvimage.Entity.ProductImage;
import com.example.csvimage.Repository.ProductRepository;
@Service
public class WebhookService {
    private ProductRepository productRepository;

    @Autowired
    public WebhookService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void triggerWebhook(String requestId){
        String payload = "{\"requestId\": \"" + requestId + "\", \"status\": \"completed\"}";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<String> request = new HttpEntity<>(payload, headers);

        RestTemplate restTemplate=new RestTemplate();
        String url="http://localhost:8080/images/download";

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        
        System.out.println("Webhook triggered with response: " + response.getBody());
    }
}
