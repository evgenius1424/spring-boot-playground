package com.github.evgenius1424.spring_boot_playground;

import org.springframework.boot.SpringApplication;

public class TestSpringBootPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringBootPlaygroundApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
