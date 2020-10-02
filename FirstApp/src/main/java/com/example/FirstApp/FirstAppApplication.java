package com.example.FirstApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstAppApplication {

	/*@Value("${spring.jpa.database-platform}")
	private String userBucketPath;*/

	public static void main(String[] args) {
		SpringApplication.run(FirstAppApplication.class, args);
	}

}
