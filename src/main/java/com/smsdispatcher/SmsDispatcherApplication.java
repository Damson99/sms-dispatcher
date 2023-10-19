package com.smsdispatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SmsDispatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsDispatcherApplication.class, args);
	}
}
