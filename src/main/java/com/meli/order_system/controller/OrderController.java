package com.meli.order_system.controller; // Correct package name

import com.meli.order_system.model.Order;
import com.meli.order_system.repository.OrderRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * REST Controller for managing orders.
 * Exposes full CRUD endpoints with pagination, sorting, and validation.
 * This class is the C (Controller) in the MVC pattern for the API layer.
 */
@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Management", description = "API for managing customer orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * READ (All) operation: Retrieves a paginated and sorted list of all orders.
     * This method leverages Spring Data's Pageable feature to handle pagination and sorting
     * directly from the URL query parameters.
     *
     * @param pageable Object created by Spring from URL parameters like ?page=, ?size=, and ?sort=.
     * @return A Page object containing the list of orders for the current page and pagination metadata.
     */
    @Operation(summary = "Get a paginated list of all orders",
               description = "Returns a list of orders. Supports pagination and sorting via URL parameters (e.g., ?page=0&size=5&sort=customerName,desc).")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of orders")
    @GetMapping
    public Page<Order> getAllOrders(
        @Parameter(description = "Pagination and sorting information") Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    /**
     * READ (Single) operation: Retrieves a single order by its unique ID.
     *
     * @param id The unique identifier of the order.
     * @return A ResponseEntity containing the found Order with a 200 OK status,
     *         or a 404 Not Found status if the order does not exist.
     */
    @Operation(summary = "Get a single order by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the order"),
        @ApiResponse(responseCode = "404", description = "Order not found with the provided ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(
        @Parameter(description = "ID of the order to be retrieved") @PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        // Use functional style with map() to handle the Optional result.
        return order.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * CREATE operation: Creates a new order.
     * The @Valid annotation triggers validation based on the constraints defined in the Order model.
     *
     * @param order The Order object sent in the request body.
     * @return A ResponseEntity containing the newly created Order with its generated ID and a 201 Created status.
     */
    @Operation(summary = "Create a new order")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Order created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data provided")
    })
    @PostMapping
    public ResponseEntity<Order> createOrder(
        @Parameter(description = "Order object that needs to be added") @Valid @RequestBody Order order) {
        Order savedOrder = orderRepository.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    /**
     * UPDATE operation: Updates an existing order identified by its ID.
     * It finds the existing order and applies the changes from the request body.
     *
     * @param id The ID of the order to be updated.
     * @param orderDetails An Order object from the request body with the new data.
     * @return A ResponseEntity containing the updated Order with a 200 OK status,
     *         or a 404 Not Found if the order does not exist.
     */
    @Operation(summary = "Update an existing order by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data provided"),
        @ApiResponse(responseCode = "404", description = "Order not found with the provided ID")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(
        @Parameter(description = "ID of the order to be updated") @PathVariable Long id,
        @Parameter(description = "Updated order object") @Valid @RequestBody Order orderDetails) {
        return orderRepository.findById(id)
                .map(order -> {
                    // Update fields of the existing order
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

    /**
     * DELETE operation: Deletes an order by its ID.
     *
     * @param id The ID of the order to be deleted.
     * @return An empty ResponseEntity with a 200 OK status on successful deletion,
     *         or a 404 Not Found if the order does not exist.
     */
    @Operation(summary = "Delete an order by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Order deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Order not found with the provided ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(
        @Parameter(description = "ID of the order to be deleted") @PathVariable Long id) {
        return orderRepository.findById(id)
                .map(order -> {
                    orderRepository.delete(order);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}