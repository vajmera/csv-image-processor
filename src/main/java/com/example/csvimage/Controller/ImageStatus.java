package com.example.csvimage.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.csvimage.Entity.ProductImage;
import com.example.csvimage.Repository.ProductRepository;
@RestController
@RequestMapping("product/")
public class ImageStatus {
    private ProductRepository productRepository;

    @Autowired
    public ImageStatus(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("status")
public ResponseEntity<List<String>> getStatus(@RequestParam("id") String requestId){
    if (requestId == null || requestId.isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    List<ProductImage> lst = productRepository.findByRequestId(requestId);
    if (lst.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    List<String>statusString=new ArrayList<>();
    for(ProductImage pimage:lst){
        statusString.add(pimage.getStatus());
    }
    return ResponseEntity.ok(statusString);
}
}
