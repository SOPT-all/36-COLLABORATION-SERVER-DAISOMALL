package com.sopt.DaisoMall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DaisoMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaisoMallApplication.class, args);
	}

}
