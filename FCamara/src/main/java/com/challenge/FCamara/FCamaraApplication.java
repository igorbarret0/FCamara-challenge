package com.challenge.FCamara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FCamaraApplication {

	public static void main(String[] args) {
		SpringApplication.run(FCamaraApplication.class, args);
	}

}
