package com.umang.orderservice.service;


import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
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
            Optional<Product> product = productRepository.findById(orderProduct.getProduct().getId());
            if (product.isPresent()) {
                BigDecimal price = product.get().getPrice().multiply(BigDecimal.valueOf(orderProduct.getQuantity()));
                totalPrice = totalPrice.add(price);
            }
        }
        return totalPrice;
    }
}
