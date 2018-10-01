package com.tm.tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tm.*")
public class GetBetterToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetBetterToolApplication.class, args);
	}
}
