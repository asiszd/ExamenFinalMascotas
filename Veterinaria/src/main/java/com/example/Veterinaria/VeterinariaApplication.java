package com.example.Veterinaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class VeterinariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeterinariaApplication.class, args);
	}

}
