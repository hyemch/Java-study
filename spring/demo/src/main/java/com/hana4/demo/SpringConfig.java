package com.hana4.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hana4.demo.repository.UserRepository;
import com.hana4.demo.repository.VolatileUserRepository;
import com.hana4.demo.service.UserService;

@Configuration
public class SpringConfig {

	@Bean
	public UserService userService() {
		return new UserService(userRepository());
	}

	@Bean
	public UserRepository userRepository() {
		return new VolatileUserRepository();
	}

}
