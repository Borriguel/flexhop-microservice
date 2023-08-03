package dev.borriguel.orderservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaServer
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Order Service API",
				version = "1.0",
				description = "Documentation Order Service API v1.0",
				contact = @Contact(
						name = "Rodolpho Henrique",
						email = "rodolpho.omedio@gmail.com",
						url = "https://github.com/Borriguel")
		)
)
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
