package com.jaykumar.adminservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("product-service")
public interface IProductClient {

    @GetMapping("/products")
    ResponseEntity<List<Product>> getAllProducts();

    @GetMapping("/products/{id}")
    ResponseEntity<Product> getProductById(@PathVariable("id") Integer id);

    @PostMapping("/products")
    ResponseEntity<Product> createProduct(@RequestBody Product product);

    @PutMapping("/products/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product productDetails);

    @DeleteMapping("/products/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id);
}