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
    private String serialNumber;
    private String productName;
    @ElementCollection
    private List<String> imageUrls;
    @ElementCollection
    private List<String> outputImageUrls;
    private String status; // e.g., "Pending", "Processing", "Completed"
    public ProductImage(String serialNumber, String productName, List<String> imageUrls) {
        this.serialNumber = serialNumber;
        this.productName = productName;
        this.imageUrls = imageUrls;
    }
    @Override
    public String toString() {
        return "ProductImage [serialNumber=" + serialNumber + ", productName=" + productName + ", imageUrls="
                + imageUrls + ", status=" + status + "]";
    }
}
