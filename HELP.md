# Spring Bean Life Cycle Notes

## Project purpose

This project logs the lifecycle of the Spring-managed `Employee` bean. It demonstrates
construction, bean-name awareness, initialization, and destruction. The callbacks
only print messages; they do not access a database, create caches, or start jobs.

The Maven build declares Java 17 and Spring Boot 4.1.1. It includes Spring Web MVC
and Lombok, although this example declares no controllers and uses no Lombok annotations.

## Source files

* `SpringBeanLifeCycleApplication.java`: starts Spring Boot and component scanning.
* `Employee.java`: a `@Component` with the default singleton scope and bean name `employee`.
* `SpringBeanLifeCycleApplicationTests.java`: checks that the application context loads.
* `application.properties`: sets the application name to `spring-bean-life-cycle`.

## Lifecycle order in this example

| Step | Method or callback | Purpose |
| --- | --- | --- |
| 1 | `Employee()` | Constructs the bean before dependency injection. |
| 2 | `setBeanName(String name)` | Supplies the registered bean name through `BeanNameAware`. |
| 3 | `initilize()` with `@PostConstruct` | Performs initialization after dependency injection. |
| 4 | `afterPropertiesSet()` | Invokes the `InitializingBean` initialization callback. |
| 5 | Bean available for use | The initialized singleton remains in the application context. |
| 6 | `clean()` with `@PreDestroy` | Performs cleanup when the context closes normally. |
| 7 | `destroy()` | Invokes the `DisposableBean` destruction callback. |

`Employee` currently has no injected dependencies. Its method name `initilize()`
is retained as written; the annotation determines its lifecycle role.
The annotation callbacks use `jakarta.annotation` imports.

Both initialization mechanisms and both destruction mechanisms are included for
comparison. When applying them to real resources, avoid performing the same work
twice. Construction happens before injected dependencies are available, so setup
that requires those dependencies belongs in an initialization callback.

Expected application messages at startup, interspersed with Spring Boot logs:

```text
Employee Constructor
Bean Name :employee
Employee @PostConstruct
Employee afterPropertiesSet(-)
```

Expected messages when the application context closes normally:

```text
Employee @PreDestroy
Employee destroy
```

Destruction callbacks depend on orderly context shutdown; forcibly terminating
the process may prevent them from running. These notes describe the singleton
bean in this project; Spring does not automatically invoke destruction callbacks
for prototype beans.

## Run and check

Use a JDK compatible with the build's Java 17 target and a configured `JAVA_HOME`.
From the project directory in PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Stop the application normally with Ctrl+C to observe cleanup. The Web MVC
dependency starts an embedded web server, but there are no application endpoints
to call in this example.

Run the existing context-loading test:

```powershell
.\mvnw.cmd test
```

On macOS or Linux, use `./mvnw` instead of `.\mvnw.cmd`. The wrapper may need
network access to download Maven and project dependencies on first use.
The existing test verifies startup only; it does not assert lifecycle ordering
or shutdown behavior.

## Package naming
The following was discovered as part of building this project:

* The original package name 'com.durgasoft.spring-bean-life-cycle' is invalid and this project uses 'com.durgasoft.spring_bean_life_cycle' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.1.1/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.1.1/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/4.1.1/reference/web/servlet.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.
