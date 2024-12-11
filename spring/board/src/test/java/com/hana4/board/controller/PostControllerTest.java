package com.hana4.board.controller;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hana4.board.dto.PostDTO;
import com.hana4.board.dto.PostMapper;
import com.hana4.board.entity.User;
import com.hana4.board.repository.PostRepository;
import com.hana4.board.repository.UserRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class PostControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ObjectMapper objectMapper;

	private User user ;

	@BeforeEach
	void setUp() {
	// 	userRepository.deleteAll();
	// 	for (int i = 0; i <= 5; i++) {
	// 		user = new User();
	// 		user.setId(UUID.randomUUID().toString());
	// 		user.setName("Kim" + i);
	// 	}
		user = userRepository.findAll().get(0);
	}
	@Test
	@Order(1)
	void testGetAllPosts() throws Exception {

		PostDTO postDTO1 = new PostDTO(null, null, null, "Post 1", user.getId(), "Body 1");
		PostDTO postDTO2 = new PostDTO(null, null, null, "Post 2", user.getId(), "Body 2");
		PostDTO postDTO3 = new PostDTO(null, null, null, "Post 3", user.getId(), "Body 3");
		postRepository.save(PostMapper.toEntity(postDTO1, user));
		postRepository.save(PostMapper.toEntity(postDTO2, user));
		postRepository.save(PostMapper.toEntity(postDTO3, user));

		mockMvc.perform(get("/posts"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(3))
			.andExpect(jsonPath("$[0].title").value("Post 1"))
			.andExpect(jsonPath("$[0].body").value("Body 1"))
			.andExpect(jsonPath("$[0].writer").value(user.getId()))
			.andExpect(jsonPath("$[1].title").value("Post 2"))
			.andExpect(jsonPath("$[1].body").value("Body 2"))
			.andExpect(jsonPath("$[1].writer").value(user.getId()))
			.andExpect(jsonPath("$[2].title").value("Post 3"))
			.andExpect(jsonPath("$[2].body").value("Body 3"))
			.andExpect(jsonPath("$[2].writer").value(user.getId()))
			.andDo(print());
	}


	@Test
	@Order(2)
	void testCreatePost() throws Exception {
		PostDTO postDTO = new PostDTO();
		postDTO.setTitle("Create Post Title");
		postDTO.setBody("Create Body");
		postDTO.setWriter(user.getId());

		String requestBody = objectMapper.writeValueAsString(postDTO);

		mockMvc.perform(post("/posts")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.title").value("Create Post Title"))
			.andExpect(jsonPath("$.body").value("Create Body"))
			.andExpect(jsonPath("$.writer").value(user.getId()))
			.andDo(print());
	}

	@Test
	@Order(3)
	void testGetPostById() throws Exception {
		PostDTO postDTO = new PostDTO(null, null, null, "Post Detail", user.getId(), "Body Detail");
		Long postId = postRepository.save(PostMapper.toEntity(postDTO, user)).getId();

		mockMvc.perform(get("/posts/" + postId))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(postId))
			.andExpect(jsonPath("$.title").value("Post Detail"))
			.andExpect(jsonPath("$.body").value("Body Detail"))
			.andDo(print());
	}

	@Test
	@Order(4)
	void testUpdatePost() throws Exception {
		PostDTO postDTO = new PostDTO(null, null, null, "Original Title", user.getId(), "Original Body");
		Long postId = postRepository.save(PostMapper.toEntity(postDTO, user)).getId();

		PostDTO updatePost = new PostDTO(null, null, null, "Updated Title", user.getId(), "Updated Body");

		String requestBody = objectMapper.writeValueAsString(updatePost);

		mockMvc.perform(patch("/posts/" + postId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value("Updated Title"))
			.andExpect(jsonPath("$.body").value("Updated Body"))
			.andDo(print());
	}

	@Test
	@Order(5)
	void testDeletePost() throws Exception {
		PostDTO postDTO = new PostDTO(null, null, null, "Title to Delete", user.getId(), "Body to Delete");
		Long postId = postRepository.save(PostMapper.toEntity(postDTO, user)).getId();

		mockMvc.perform(delete("/posts/" + postId))
			.andExpect(status().isNoContent())
			.andDo(print());

		assertThat(postRepository.findById(postId)).isEmpty();
	}
}
