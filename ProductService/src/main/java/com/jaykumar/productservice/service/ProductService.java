package com.jaykumar.productservice.service;

import com.jaykumar.productservice.entity.Product;
import com.jaykumar.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).get();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public void updateInventory(Integer quantity, Integer productId) {
        Product product = getProductById(productId);
        if(product.getStock() < quantity) {
            System.out.println("quantity ordered is less than inventory on hand");
        } else {
            product.setStock(product.getStock() - quantity);
            System.out.println("Order dispatched");
            productRepository.save(product);
        }
    }
}
