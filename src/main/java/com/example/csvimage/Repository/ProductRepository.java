package com.example.csvimage.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.csvimage.Entity.ProductImage;

@Repository
public interface ProductRepository extends JpaRepository<ProductImage,Long>{
    // List<ProductImage> findById(Integer requestId);
}
