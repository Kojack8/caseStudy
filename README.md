# Video Production Shopping Service
This application is designed as a simple store. Transaction handling does not occur in this program but can be easily implemented using [Stripe for React](https://www.npmjs.com/package/react-stripe-checkout). While this deployment is model for use as a video production shopping service, it can easily be modified to sell any type of product.

## Installation
---
1. ``` git clone https://github.com/Kojack8/caseStudy.git ```

2. Built RDBMS objects by following [SQLBuiltText.md](./SQLBuildText.md)

3. Set the following values in application.properties
```
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name =
```

4. Run the program with
```
./mvnw spring-boot:run
```

## Built with
---
* Java 1.8
* Spring Web
* Spring Data JPA
* Spring Security
* Hibernate ORM
* MySQL Database
* H2 (Unit Testing)
* Lombok (Slf4j logging)
* [unDraw illustrations](https://undraw.co/illustrations)

## License
---
This project is licensed under the MIT License - see the [LICENSE.md](./LICENSE.md) file for more details

