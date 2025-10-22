package com.meli.order_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * Represents the Order entity for the online store.
 * This class is mapped to a database table named 'orders'.
 */
@Entity
@Table(name = "orders") 
@Data // Anotación de Lombok para generar getters, setters, toString, etc.
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer name cannot be blank") // VALIDACIÓN
    @Size(min = 3, max = 100, message = "Customer name must be between 3 and 100 characters") // VALIDACIÓN
    private String customerName;

    @NotBlank(message = "Product cannot be blank") // VALIDACIÓN
    private String product;

    @NotNull(message = "Quantity cannot be null") // VALIDACIÓN
    @Min(value = 1, message = "Quantity must be at least 1") // VALIDACIÓN
    private int quantity;

    @NotNull(message = "Price cannot be null") // VALIDACIÓN
    @Positive(message = "Price must be a positive value") // VALIDACIÓN
    private double price;
    
    private String status = "PENDING"; // Default status
}