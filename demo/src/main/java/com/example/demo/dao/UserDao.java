package com.example.demo.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	
private final static  List<UserDetails> APPLCATION_USERS =  Arrays.asList(
			
			new User(
					"df@gmail.com",
					"1234",
//					"$2a$10$mRxtycpRMFlWqRliJ6YXKuilIowR9024EiYPBy7pzbUcV/nVp3wbG",
					Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
					
					),
			new User(
					"kf@gmail.com",
					"1234",
					Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
					
					)
			
			);
	


public UserDetails getUserByEmail(String email) {
	

	
				return APPLCATION_USERS
						.stream()
						.filter(l -> l.getUsername().equals(email))
						.findFirst()
						.orElseThrow(()->new UsernameNotFoundException("No user found"));
	}
	
}


