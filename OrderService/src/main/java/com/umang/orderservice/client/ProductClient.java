package com.umang.orderservice.client;

import com.umang.orderservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://PRODUCT-SERVICE/api/products")
public interface ProductClient {

    @GetMapping("/{id}")
    ProductDto getProductById(@PathVariable("id") Integer id);
}
