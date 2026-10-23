package com.durgasoft.spring_bean_life_cycle;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBeanLifeCycleApplicationTests {

	/**
	 * Checks that the Spring application context starts without configuration errors.
	 * Context startup invokes bean initialization, but this test does not assert callback order.
	 */
	@Test
	void contextLoads() {
	}

}
