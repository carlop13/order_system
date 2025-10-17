package com.meli.order_system.repository;

import com.meli.order_system.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Order entity.
 * This interface handles all standard database operations (CRUD) automatically.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Spring Data JPA creará las implementaciones de findById, save, deleteById, etc.
}