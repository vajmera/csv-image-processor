package com.example.csvimage.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requestId;
    private String serialNumber;
    private String productName;
    private List<String> imageUrls;
    private List<String> outputImageUrls;
    private String status; 
    public ProductImage(String serialNumber, String productName, List<String> imageUrls, String requestId) {
        this.serialNumber = serialNumber;
        this.productName = productName;
        this.imageUrls = imageUrls;
        this.requestId=requestId;
    }
    @Override
    public String toString() {
        return "ProductImage [serialNumber=" + serialNumber + ", productName=" + productName + ", imageUrls="
                + imageUrls + ", status=" + status + "]";
    }
}
