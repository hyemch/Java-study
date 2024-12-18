package com.hana4.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hana4.demo.dto.UserDTO;

@SpringBootTest
public class ApiServerTest {
	@Autowired
	ApiService service;

	@Test
	void getUserTest() {
		final Long ID = 1L;
		UserDTO user = service.getUser(ID);
		// Optional<User> ouser = Optional.of(new User(1L, "AA11", (short)33));
		// Mockito.when(apiDAO.select(1L)).thenReturn(ouser);
		// UserDTO user = apiService.getUser(1L);

		// assertThat(user.getId()).isEqualTo(ID);
		// assertThat(user.getName()).isEqualTo("Hana");
		// assertThat(user.getName()).isEqualTo("AA10");
		// assertThat(user.getName()).isNotNull();
	}
}
