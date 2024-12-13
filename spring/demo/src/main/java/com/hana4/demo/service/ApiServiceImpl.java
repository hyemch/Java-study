package com.hana4.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hana4.demo.dao.ApiDAO;
import com.hana4.demo.domain.User;
import com.hana4.demo.dto.UserDTO;

@Service
public class ApiServiceImpl implements ApiService {
	private final ApiDAO dao;

	public ApiServiceImpl(ApiDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<UserDTO> getUsers() {
		List<User> users = dao.selectAll();

		return users.stream()
			.map(u -> UserDTO.builder().id(u.getId()).name(u.getName()).age(u.getAge()).build())
			.toList();
	}

	@Override
	public UserDTO getUser(Long id) {
		// Optional<User> ouser = dao.select(id);
		// if (ouser.isPresent()) {
		// 	User user = ouser.get();
		// 	UserDTO dto = new UserDTO(user.getId(), user.getName(), user.getAge());
		// }
		return dao.select(id).map(User::toDTO).orElse(null);
	}

	@Override
	public UserDTO addUser(String name, short age) {
		return dao.insert(name, age).toDTO();

	}

	@Override
	public UserDTO modifyUser(UserDTO userDTO) {
		return dao.update(userDTO).toDTO();
	}

	@Override
	public UserDTO removeUser(Long id) {
		return dao.delete(id).toDTO();
	}
}
