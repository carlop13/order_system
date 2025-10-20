package com.meli.order_system;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class OrderSystemApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Application context loaded successfully with 'test' profile.");
	}

}
