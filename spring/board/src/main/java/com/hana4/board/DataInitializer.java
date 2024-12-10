package com.hana4.board;

import java.util.UUID;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import com.hana4.board.entity.User;
import com.hana4.board.repository.UserRepository;

@Configuration
public class DataInitializer implements ApplicationRunner {
	UserRepository userRepository;

	public DataInitializer(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	// @Bean
	public void run(ApplicationArguments args) {
		userRepository.deleteAll();
		for (int i = 1; i <= 5; i++) {
			User user = new User();
			user.setId(UUID.randomUUID().toString());
			user.setName("Kim" + i);
			user.setEmail("kim" + i + "@example.com");
			userRepository.save(user);
		}
	}

	// @Bean
	// public ApplicationRunner initializer(UserRepository userRepository) {
	// 	return args -> {
	// 		userRepository.deleteAll();
	// 		for (int i = 1; i <= 5; i++) {
	// 			User user = new User();
	// 			user.setId(UUID.randomUUID().toString());
	// 			user.setName("Kim" + i);
	// 			user.setEmail("kim" + i + "@example.com");
	// 			userRepository.save(user);
	// 		}
	// 	};
	// }

}
