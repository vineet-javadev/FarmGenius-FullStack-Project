package com.backend.AgriSmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class AgriSmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgriSmartApplication.class, args);

		log.info("\n\nServer Started Successfully.\n");
	}

}
