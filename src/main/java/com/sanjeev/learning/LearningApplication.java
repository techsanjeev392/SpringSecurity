package com.sanjeev.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningApplication{
//implements CommandLineRunner{
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(LearningApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		
//		System.out.println(passwordEncoder.encode("xyz"));
//	}
}
