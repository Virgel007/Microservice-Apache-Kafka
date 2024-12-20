package org.example.mc_shipping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class McShippingApplication {

	public static void main(String[] args) {
		SpringApplication.run(McShippingApplication.class, args);
	}
}