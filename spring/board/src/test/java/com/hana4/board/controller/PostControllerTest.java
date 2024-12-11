package com.hana4.board.controller;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

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
import com.hana4.board.entity.Post;
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
	private final List<PostDTO> samplePosts = new ArrayList<>();

	@BeforeEach
	void setUp() {
		samplePosts.clear();
		postRepository.deleteAll();
		user = userRepository.findAll().get(0);
		for (int i = 1; i <= 3; i++) {
			PostDTO postDTO = new PostDTO("Post title" + i, user.getId(), "Post body" + i);
			Post savedPost = postRepository.save(PostMapper.toEntity(postDTO, user));
			postDTO.setId(savedPost.getId());
			samplePosts.add(postDTO);
		}
	}

	@Test
	@Order(1)
	void testGetAllPosts() throws Exception {
		System.out.println("samplePosts size= " + samplePosts.size());
		mockMvc.perform(get("/posts"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(samplePosts.size()))
			.andExpect(jsonPath("$[0].id").value(samplePosts.get(0).getId()))
			.andDo(print());
	}

	@Test
	@Order(2)
	void testCreatePost() throws Exception {
		for (int i = 1; i <= 3; i++) {
			PostDTO postDTO = new PostDTO("Create Post" + i, user.getId(),
				"Post Body" + i);
			String requestBody = objectMapper.writeValueAsString(postDTO);
			mockMvc.perform(post("/posts")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.title").value(postDTO.getTitle()))
				.andExpect(jsonPath("$.body").value(postDTO.getBody()))
				.andExpect(jsonPath("$.writer").value(user.getId()))
				.andDo(print());
			samplePosts.add(postDTO);
		}
	}

	@Test
	@Order(3)
	void testGetPostById() throws Exception {

		Long postId = samplePosts.get(0).getId();
		mockMvc.perform(get("/posts/" + postId))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(postId))
			.andExpect(jsonPath("$.title").value(samplePosts.get(0).getTitle()))
			.andExpect(jsonPath("$.body").value(samplePosts.get(0).getBody()))
			.andDo(print());
	}

	@Test
	@Order(4)
	void testUpdatePost() throws Exception {
		Long postId = samplePosts.get(0).getId();
		PostDTO updatePost = new PostDTO("Updated Title", user.getId(), "Updated Body");

		String requestBody = objectMapper.writeValueAsString(updatePost);

		mockMvc.perform(patch("/posts/" + postId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value("Updated Title"))
			.andExpect(jsonPath("$.body").value("Updated Body"))
			.andDo(print());
		assertThat(postRepository.findById(postId)).get().hasFieldOrPropertyWithValue("title", updatePost.getTitle());
	}

	@Test
	@Order(5)
	void testDeletePost() throws Exception {
		Long postId = samplePosts.get(1).getId();

		mockMvc.perform(delete("/posts/" + postId))
			.andExpect(status().isNoContent())
			.andDo(print());

		assertThat(postRepository.findById(postId)).isEmpty();
	}
}
