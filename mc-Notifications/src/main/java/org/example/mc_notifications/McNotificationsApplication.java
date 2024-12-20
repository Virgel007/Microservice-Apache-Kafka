package org.example.mc_notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class McNotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(McNotificationsApplication.class, args);
	}
}