package com.example.demo.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class UserDao {

	private final static List<UserDetails> APPLICATION_USERS;

	static {
		// Create a specific date
		Calendar calendar = Calendar.getInstance();
		calendar.set(2024, Calendar.NOVEMBER, 11); // Month is 0-based, so November is 10
		Date date = calendar.getTime();
		String newDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

		APPLICATION_USERS = Arrays.asList(
				new User(
						"df@gmail.com",
						new BCryptPasswordEncoder().encode("1234"),
						Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
				),
				new User(
						"kf@gmail.com",
						new BCryptPasswordEncoder().encode("1234"),
						Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
				)
		);
	}

public UserDetails getUserByEmail(String email) {
	

	
				return APPLICATION_USERS
						.stream()
						.filter(l -> l.getUsername().equals(email))
						.findFirst()
						.orElseThrow(()->new UsernameNotFoundException("No user found"));
	}
	
}


