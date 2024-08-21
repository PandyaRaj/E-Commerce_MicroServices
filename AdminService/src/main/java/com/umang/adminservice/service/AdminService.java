package com.umang.adminservice.service;
import com.umang.adminservice.client.ProductServiceClient;
import com.umang.adminservice.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private ProductServiceClient productServiceClient;

    public ResponseEntity<ProductDto> getProduct(Integer id) {
        return productServiceClient.getProduct(id);
    }

    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return productServiceClient.getAllProducts();
    }

    public ResponseEntity<ProductDto> createProduct(ProductDto product) {
        return productServiceClient.createProduct(product);
    }

    public ResponseEntity<ProductDto> updateProduct(Integer id, ProductDto product) {
        return productServiceClient.updateProduct(id, product);
    }

    public ResponseEntity<String> deleteProduct(Integer id) {
        return productServiceClient.deleteProduct(id);
    }
}