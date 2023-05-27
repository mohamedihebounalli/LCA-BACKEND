package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//	@GetMapping
//	public List<Account> getAllAccount (){
//		return List.of(
//				new Account(1,"Mohamed","Islem",new Date("03/11/2000"),"95776336","email@gmail.com",Role.CUSTOMER)
//				);
//	}

//	@GetMapping("/")
//	public List<String> getAllAccount (){
//		return List.of(
//				"hello","world"
//		);
//	}
}