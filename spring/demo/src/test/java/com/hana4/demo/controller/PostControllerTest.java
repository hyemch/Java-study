package com.hana4.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.hana4.demo.dto.PostDTO;
import com.hana4.demo.service.PostService;

@WebMvcTest(PostController.class)
public class PostControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PostService service;

	@Test
	void getPostTest() throws Exception {
		final String ID = "XX";
		final LocalDateTime now = LocalDateTime.now();
		final PostDTO dto = new PostDTO(ID, "title", "writer", now, now, "body");

		BDDMockito.given(service.getPost(ID)).willReturn(dto);
		final String url = "/posts/" + ID;
		mockMvc.perform(get(url))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").exists())
			.andExpect(jsonPath("$.title").value("title"))
			.andExpect(jsonPath("$.writer").value("writer"))
			.andDo(print());
	}

}
