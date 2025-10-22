// OrderControllerIntegrationTest.java
package com.meli.order_system;

import com.meli.order_system.model.Order;
import com.meli.order_system.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Usa la configuración de application-test.properties
class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    // Limpia la base de datos después de cada prueba
    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
    }

    @Test
    void whenGetOrderById_andOrderExists_thenReturns200() throws Exception {
        // Given: una orden guardada en la BD
        Order order = new Order();
        order.setCustomerName("Test Customer");
        order.setProduct("Test Product");
        order.setQuantity(1);
        order.setPrice(100.0);
        Order savedOrder = orderRepository.save(order);

        // When: se hace una petición GET
        // Then: se espera un 200 OK y los datos correctos
        mockMvc.perform(get("/api/orders/" + savedOrder.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Test Customer"));
    }

    @Test
    void whenGetOrderById_andOrderDoesNotExist_thenReturns404() throws Exception {
        // When: se hace una petición GET con un ID que no existe
        // Then: se espera un 404 Not Found
        mockMvc.perform(get("/api/orders/999"))
                .andExpect(status().isNotFound());
    }
}