package com.renjamio.challenge_mendel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ChallengeMendelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeMendelApplication.class, args);
	}

}
