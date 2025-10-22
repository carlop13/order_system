package com.meli.order_system.repository; // Tu nombre de paquete correcto

import com.meli.order_system.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Order entity.
 * This interface handles all standard database operations (CRUD),
 * as well as pagination and sorting, by extending JpaRepository.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> { 
    // JpaRepository provides save(), findById(), findAll(Pageable), deleteById(), etc., all out of the box.
}