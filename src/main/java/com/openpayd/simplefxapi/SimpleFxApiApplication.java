package com.openpayd.simplefxapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SimpleFxApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleFxApiApplication.class, args);
	}

}
