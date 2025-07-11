package com.mindprove.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;

@SpringBootApplication
public class BookCurdTask1Application {

	public static void main(String[] args) {
		SpringApplication.run(BookCurdTask1Application.class, args);
		System.out.println("Project started....");
	}
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	

}
