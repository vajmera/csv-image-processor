package com.example.csvimage.Service;

import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.csvimage.Entity.ProductImage;
import com.example.csvimage.Repository.ProductRepository;

@Service
public class ImageCompressionService {
    private ProductRepository productRepository;
    @Autowired
    public ImageCompressionService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Async
    public CompletableFuture<Void> processImages(List<ProductImage> products, String requestId) {
        for (ProductImage product : products) {
            // For each image URL, compress the image and store the output URL
            List<String> outputUrls = new ArrayList<>();

            for (String url : product.getImageUrls()) {
                String compressedImageUrl = compressImage(url);
                outputUrls.add(compressedImageUrl);
                // productRepository.save(outputUrls);
            }
            
            // Save output URLs and processing status in the database
        }
        // After processing, update the status and trigger the webhook
        return CompletableFuture.completedFuture(null);
    }

    private String compressImage(String imageUrl) {
    // Compress the image and return the new image URL
    return imageUrl.replace("public", "compressed"); // mocking compression
    }
}
