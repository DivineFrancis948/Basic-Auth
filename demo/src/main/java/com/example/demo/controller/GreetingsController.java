package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/greet")
public class GreetingsController {
	
	@GetMapping("/hi")
	public ResponseEntity<String>getHello() {
		return ResponseEntity.ok("Heloo");
	}
	
	@GetMapping("/bye")
	public ResponseEntity<String>getBye() {
		return ResponseEntity.ok("Bye");
	}
	

}
