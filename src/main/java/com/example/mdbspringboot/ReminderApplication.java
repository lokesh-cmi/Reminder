package com.example.mdbspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.mdbspringboot.controller", "com.example.mdbspringboot.repository", "com.example.mdbspringboot.model"})


public class ReminderApplication  {


	public static void main(String[] args) {
		SpringApplication.run(ReminderApplication.class, args);
	}



	//Create


}
