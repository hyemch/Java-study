package com.hana4.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hana4.demo.dao.ApiDAO;
import com.hana4.demo.dao.ApiDAOImpl;
import com.hana4.demo.repository.ApiRepository;
import com.hana4.demo.repository.JpaUserRepository;
import com.hana4.demo.repository.UserRepository;
import com.hana4.demo.service.UserService;

import jakarta.persistence.EntityManager;

@Configuration
public class SpringConfig {

	private final EntityManager em;
	private final ApiRepository apiRepository;

	public SpringConfig(EntityManager em, ApiRepository apiRepository) {
		this.em = em;
		this.apiRepository = apiRepository;
	}

	@Bean
	public UserService userService() {
		return new UserService(userRepository());
	}

	@Bean
	public UserRepository userRepository() {
		return new JpaUserRepository(em);
		// return new VolatileUserRepository();
	}

	@Bean
	public ApiDAO apiDao() {
		return new ApiDAOImpl(apiRepository);
	}

}
