package com.example.csvimage.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.csvimage.Entity.ProductImage;
import com.example.csvimage.Repository.ProductRepository;

@Service
public class ImageCompressionService {
    private ProductRepository productRepository;
    private WebhookService webhookService;
    
    @Autowired
    public ImageCompressionService(ProductRepository productRepository, WebhookService webhookService) {
        this.productRepository = productRepository;
        this.webhookService = webhookService;
    }

    @Async
    public CompletableFuture<Void> processImages(List<ProductImage> products) {
        String requestId="";
        for (ProductImage product : products) {
            // For each image URL, compress the image and store the output URL
            List<String> outputUrls = new ArrayList<>();

            for (String url : product.getImageUrls()) {
                String compressedImageUrl = compressImage(url);
                outputUrls.add(compressedImageUrl);
            }
            product.setOutputImageUrls(outputUrls);
            product.setStatus("Compressed");
            productRepository.save(product);
            // Save output URLs and processing status in the database
        }
        for(ProductImage product:products){
            product.setStatus("Successfully Compressed");
            productRepository.save(product);
        }
        requestId=products.get(0).getRequestId();

         webhookService.triggerWebhook(requestId);
        return CompletableFuture.completedFuture(null);
    }

    private String compressImage(String imageUrl) {
        return imageUrl.replace("image", "image-output"); 
    }
}
