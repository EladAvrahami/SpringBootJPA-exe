package com.jb.targil_spring1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.jb.targil_spring1.util.Art;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication //component scan , configuration scan
public class TargilSpring1Application {

	public static void main(String[] args) {
		SpringApplication.run(TargilSpring1Application.class, args);
		System.out.println(Art.localhost);
	}



}
