package com.jaykumar.productservice;

import com.jaykumar.productservice.dto.Order;
import com.jaykumar.productservice.dto.OrderItem;
import com.jaykumar.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

@Configuration
public class Config {
    Logger logger = LoggerFactory.getLogger(Config.class);

    @Autowired
    private ProductService productService;

    @Bean
    public Consumer<Order> checkInventory() {
        return(Order order) -> {
            System.out.println("message received : "+ order.toString());
            System.out.println(order.getOrderDate());
            for (OrderItem item : order.getItems()) {
                productService.updateInventory(item.getQuantity(), item.getProductId());
            }

        };
    }
}
