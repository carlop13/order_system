package com.meli.order_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    private String customerName;
    private String product;
    private int quantity;
    private double price;
    private String status = "PENDING"; // Default status
}