package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtUtils;
import com.example.demo.controller.dto.AuthenticationRequest;
import com.example.demo.dao.UserDao;

import lombok.NoArgsConstructor;
import org.springframework.web.context.annotation.RequestScope;


@RestController
@RequestMapping("/api/auth")
//@NoArgsConstructor
public class AuthenticationController {
	
//	@Autowired
//	AuthenticationManager authenticationManager;
////	
//	@Autowired
//	UserDao userDao;
//	UserDetailsService userDetailsService;
////	
//	@Autowired
//	JwtUtils jwtUtils;
	
	
	private final AuthenticationManager authenticationManager;
	private final UserDao userDao;
	private final JwtUtils jwtUtils;
	private final PasswordEncoder passwordEncoder;

	
	public AuthenticationController(AuthenticationManager authenticationManager,
			UserDao userDao,
	 JwtUtils jwtUtils,PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.userDao = userDao;
		this.jwtUtils = jwtUtils;
		this.passwordEncoder = passwordEncoder;

	}
	
	
	
	@PostMapping("/auth")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){



		System.out.println((request.getEmail()+"<---> "+request.getPassword()));

//			authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//					);
		try {
			UserDetails user = userDao.getUserByEmail(request.getEmail());

			// Manually check the password
			if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
				return ResponseEntity.ok(jwtUtils.generateToken(user));
			} else {
				return ResponseEntity.status(401).body("Invalid credentials");
			}
		} catch (UsernameNotFoundException e) {
			return ResponseEntity.status(401).body("Invalid credentials");
		} catch (Exception e) {
			return ResponseEntity.status(400).body("Some error occurred");
		}

		}

}
