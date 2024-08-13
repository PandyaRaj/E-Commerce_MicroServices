package com.umang.orderservice.service;

import com.umang.orderservice.client.ProductClient;
import com.umang.orderservice.dto.ProductDto;
import com.umang.orderservice.entity.Order;
import com.umang.orderservice.entity.OrderProduct;
import com.umang.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    public ResponseEntity<Order> getOrderById(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Order> createOrder(Order order) {
        BigDecimal totalPrice = calculateTotalPrice(order);
        order.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    public ResponseEntity<Order> updateOrder(Integer id, Order order) {
        if (!orderRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setId(id);
        BigDecimal totalPrice = calculateTotalPrice(order);
        order.setTotalPrice(totalPrice);
        Order updatedOrder = orderRepository.save(order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteOrder(Integer id) {
        if (!orderRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderRepository.deleteById(id);
        return new ResponseEntity<>("Order Has Been Deleted From Database!!!", HttpStatus.NO_CONTENT);
    }

    private BigDecimal calculateTotalPrice(Order order) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderProduct orderProduct : order.getOrderProducts()) {
            ProductDto product = productClient.getProductById(orderProduct.getProduct());
            BigDecimal price = product.getPrice().multiply(BigDecimal.valueOf(orderProduct.getQuantity()));
            totalPrice = totalPrice.add(price);
        }
        return totalPrice;
    }
}
