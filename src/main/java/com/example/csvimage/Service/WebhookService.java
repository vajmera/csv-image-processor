package com.example.csvimage.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
        RestTemplate restTemplate=new RestTemplate();
        String url="http://localhost:8080/images/download";
        System.out.println("Inside webhook");
        List<ProductImage> productImageLst=productRepository.findByRequestId(requestId);
        restTemplate.postForEntity(url, productImageLst, String.class);
    }
}
