package com.jaykumar.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final StreamBridge streamBridge;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, StreamBridge streamBridge) {
        this.orderRepository = orderRepository;
        this.streamBridge = streamBridge;
    }

    @Transactional
    public Order processOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        streamBridge.send("checkInventory-out-0", savedOrder);
//        for (OrderItem orderItem : order.getItems()) {
//            streamBridge.send("checkInventory-out-0", orderItem);
//        }
        return savedOrder;
    }
}
