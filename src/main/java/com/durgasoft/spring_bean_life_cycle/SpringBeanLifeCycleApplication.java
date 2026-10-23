package com.durgasoft.spring_bean_life_cycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBeanLifeCycleApplication {

	/**
	 * Starts Spring Boot and its application context, which discovers the Employee bean.
	 *
	 * @param args command-line arguments passed to Spring Boot
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBeanLifeCycleApplication.class, args);
	}

}
