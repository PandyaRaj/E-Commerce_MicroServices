package com.umang.adminservice.client;

import com.umang.adminservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @GetMapping("/products/{id}")
    ResponseEntity<ProductDto> getProduct(@PathVariable Integer id);

    @GetMapping("/products")
    ResponseEntity<List<ProductDto>> getAllProducts();

    @PostMapping("/products")
    ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product);

    @PutMapping("/products/{id}")
    ResponseEntity<ProductDto> updateProduct(@PathVariable Integer id, @RequestBody ProductDto product);

    @DeleteMapping("/products/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable Integer id);
}