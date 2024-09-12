package com.example.csvimage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CsvimageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvimageApplication.class, args);
	}

}
