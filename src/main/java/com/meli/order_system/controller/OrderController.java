package com.meli.order_system.controller;

import com.meli.order_system.model.Order;
import com.meli.order_system.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing orders.
 * Exposes CRUD endpoints for the Order entity.
 */
@RestController
@RequestMapping("/api/orders") // Base path for all endpoints in this controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    // CREATE an order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderRepository.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // READ all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // READ a single order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // UPDATE an order
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setCustomerName(orderDetails.getCustomerName());
                    order.setProduct(orderDetails.getProduct());
                    order.setQuantity(orderDetails.getQuantity());
                    order.setPrice(orderDetails.getPrice());
                    order.setStatus(orderDetails.getStatus());
                    Order updatedOrder = orderRepository.save(order);
                    return ResponseEntity.ok(updatedOrder);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE an order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(order -> {
                    orderRepository.delete(order);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}