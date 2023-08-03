package dev.borriguel.paymentservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaServer
@EnableFeignClients
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Payment Service API",
				version = "1.0",
				description = "Documentation Payment Service API v1.0",
				contact = @Contact(
						name = "Rodolpho Henrique",
						email = "rodolpho.omedio@gmail.com",
						url = "https://github.com/Borriguel")
		)
)
public class PaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

}
