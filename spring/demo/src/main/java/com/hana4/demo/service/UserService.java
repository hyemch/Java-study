package com.hana4.demo.service;

import java.util.List;
import java.util.Optional;

import com.hana4.demo.domain.User;
import com.hana4.demo.repository.UserRepository;

// @Service
public class UserService {
	private final UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public List<User> getList() {
		return repository.findAll();
	}

	public Long register(User user) {
		repository.findByName(user.getName()).ifPresent(u -> {
			throw new IllegalStateException("Duplicate: User name already exists!");
		});
		return repository.addUser(user);
	}

	public Optional<User> getUser(Long id) {
		return repository.findById(id);
	}

	public User updateUser(User user) {
		return repository.saveUser(user);
	}

	public User deleteUser(Long id) {
		return repository.deleteUser(id);
	}
}
