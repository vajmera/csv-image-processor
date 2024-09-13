package com.example.csvimage.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.csvimage.Entity.ProductImage;

@Service
public class CsvParserService {
    public List<ProductImage> csvParser(MultipartFile file){
        List<ProductImage> productImageList=new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new
        InputStreamReader(file.getInputStream()))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
           
            int len=values.length;
            for(int i=0;i+4<len;i+=5){              
                List<String>imgURLs=List.of(values[i+2],values[i+3],values[i+4]);
                ProductImage product = new ProductImage(values[i], values[i+1],imgURLs,"Not Compressed");
                productImageList.add(product);
            }
        }
        } catch (IOException e) {
             e.printStackTrace();
        }
        return productImageList;
    }
}
