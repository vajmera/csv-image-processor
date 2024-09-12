package com.example.csvimage.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.csvimage.Entity.ProductImage;
import com.example.csvimage.Repository.ProductRepository;

@Service
public class ImageService {
    private ProductRepository productRepository;

    private CsvParserService csvParserService;

    private ImageCompressionService imageCompressionService;
    @Autowired
    public ImageService(ProductRepository productRepository, CsvParserService csvParserService,
            ImageCompressionService imageCompressionService) {
        this.productRepository = productRepository;
        this.csvParserService = csvParserService;
        this.imageCompressionService = imageCompressionService;
    }

    public String processFile(MultipartFile file) {
        // Save file locally or in a temp directory
        // Parse the CSV
        // Start asynchronous processing of images
        List<ProductImage> imageList=csvParserService.csvParser(file);
        String requestId = UUID.randomUUID().toString();

        for(ProductImage product:imageList){
            product.setRequestId(requestId);
            product.setStatus("Not Processed");
            productRepository.save(product);
        }
        imageCompressionService.processImages(imageList);
       
        // Save the requestId and initial status in DB
        return requestId;
    }
}
