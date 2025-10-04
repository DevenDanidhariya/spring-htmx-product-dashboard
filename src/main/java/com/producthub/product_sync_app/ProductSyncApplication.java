package com.producthub.product_sync_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProductSyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductSyncApplication.class, args);
	}

}
