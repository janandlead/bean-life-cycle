package com.durgasoft.spring_bean_life_cycle;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class Employee implements InitializingBean, DisposableBean, BeanNameAware {

	/**
	 * Runs after dependency injection and before afterPropertiesSet().
	 * This demo logs the callback; real initialization could load reference data.
	 */
	@PostConstruct
	public void initilize() {
		System.out.println("Employee @PostConstruct");
	}

	/**
	 * Runs when the application context closes, before destroy().
	 * This demo logs the callback; owned resources could be released here.
	 */
	@PreDestroy
	public void clean() {
		System.out.println("Employee @PreDestroy");
	}

	/**
	 * Creates the bean before Spring supplies its name and invokes initialization callbacks.
	 */
	public Employee() {
		System.out.println("Employee Constructor");
	}

	/**
	 * Logs the InitializingBean callback after dependency injection and @PostConstruct.
	 * Application-specific validation or cache setup could be performed here.
	 *
	 * @throws Exception if initialization fails; this demo does not throw one
	 */
	public void afterPropertiesSet() throws Exception {
		System.out.println("Employee afterPropertiesSet(-)");
	}

	/**
	 * Logs the DisposableBean callback after @PreDestroy during context shutdown.
	 *
	 * @throws Exception if cleanup fails; this demo does not throw one
	 */
	public void destroy() throws Exception {
		System.out.println("Employee destroy");
	}
	
	/**
	 * Receives the bean's registered name before initialization callbacks run.
	 *
	 * @param name the name assigned by Spring, which defaults to "employee"
	 */
	@Override
	public void setBeanName(String name) {
		System.out.println("Bean Name :"+name);
	}
}
