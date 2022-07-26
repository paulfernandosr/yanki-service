package com.nttdata.yankiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class YankiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(YankiServiceApplication.class, args);
	}

}
