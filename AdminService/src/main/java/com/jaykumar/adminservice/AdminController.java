package com.jaykumar.adminservice;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final IProductClient productClient;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return productClient.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        return productClient.getProductById(id);
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody Product product, @RequestHeader(HttpHeaders.AUTHORIZATION) String authToken) {
        String secretKey = "YYwzirRgOIRLejdymwtcw1IyWlYGVd5sfeIT3807hfPyVTFBqjVIBI7PatO8DLXt";

        System.out.println(authToken);
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey)))
                    .build()
                    .parseClaimsJws(authToken);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return productClient.createProduct(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) {
        return productClient.updateProduct(id, productDetails);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        return productClient.deleteProduct(id);
    }
}
