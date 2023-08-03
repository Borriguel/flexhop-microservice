package dev.borriguel.productservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@OpenAPIDefinition(
        info = @Info(
                title = "Product Service API",
                version = "1.0",
                description = "Documentation Product Service API v1.0",
                contact = @Contact(
                        name = "Rodolpho Henrique",
                        email = "rodolpho.omedio@gmail.com",
                        url = "https://github.com/Borriguel")
        )
)
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
