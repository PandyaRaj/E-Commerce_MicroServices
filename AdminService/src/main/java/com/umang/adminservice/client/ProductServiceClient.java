package com.umang.adminservice.client;

import com.umang.adminservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "http://localhost:8080", name = "product-service")
public interface ProductServiceClient {

    @GetMapping("/api/products/{id}")
    ResponseEntity<ProductDto> getProduct(@PathVariable Integer id);

    @GetMapping("/api/products")
    ResponseEntity<List<ProductDto>> getAllProducts();

    @PostMapping("/api/products")
    ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product);

    @PutMapping("/api/products/{id}")
    ResponseEntity<ProductDto> updateProduct(@PathVariable Integer id, @RequestBody ProductDto product);

    @DeleteMapping("/api/products/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable Integer id);
}