package org.example.mc_orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class McOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(McOrdersApplication.class, args);
	}
}