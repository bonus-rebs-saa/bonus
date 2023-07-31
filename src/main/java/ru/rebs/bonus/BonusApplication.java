package ru.rebs.bonus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class BonusApplication {

	public static void main(String[] args) {
		SpringApplication.run(BonusApplication.class, args);
	}

}
