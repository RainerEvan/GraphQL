package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.config.SpringAuditorAware;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class DemoApplication {

	@Bean
	public AuditorAware<String> auditorAware(){
		return new SpringAuditorAware();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
