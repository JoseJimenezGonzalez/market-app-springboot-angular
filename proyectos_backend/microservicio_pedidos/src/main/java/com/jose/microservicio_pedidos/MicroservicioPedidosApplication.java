package com.jose.microservicio_pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class MicroservicioPedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioPedidosApplication.class, args);
	}

	@Bean
	public RestClient getClient(){
		return RestClient.create();
	}

}
