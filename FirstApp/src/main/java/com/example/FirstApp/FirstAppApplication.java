package com.example.FirstApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.FirstApp")

public class FirstAppApplication {

	/*@Value("${spring.jpa.database-platform}")
	private String userBucketPath;*/

	public static void main(String[] args) {
		SpringApplication.run(FirstAppApplication.class, args);
	}

}
