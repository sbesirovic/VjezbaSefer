package com.example.FirstApp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication/*(exclude = {DataSourceAutoConfiguration.class })*/
public class FirstAppApplication {

	/*@Value("${spring.jpa.database-platform}")
	private String userBucketPath;*/

	public static void main(String[] args) {
		SpringApplication.run(FirstAppApplication.class, args);
	}

}
