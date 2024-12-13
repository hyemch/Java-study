package com.hana4.board.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hana4.board.dto.PostDTO;
import com.hana4.board.entity.User;
import com.hana4.board.repository.CommentRepository;
import com.hana4.board.repository.PostRepository;
import com.hana4.board.repository.UserRepository;
import com.hana4.board.service.PostService;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private User user ;
	private final List<PostDTO> samplePosts = new ArrayList<>();
	@Autowired
	private PostService postService;

	@BeforeAll
	void setUp(
		@Autowired CommentRepository commentRepository, @Autowired PostRepository postRepository,
		@Autowired UserRepository userRepository) {
		commentRepository.deleteAll();
		postRepository.deleteAll();
		user = userRepository.findAll().get(0);

		for (int i = 0; i < 2; i++) {
			PostDTO dto = new PostDTO();
			dto.setWriter(user.getId());
			dto.setTitle("Post" + i);
			dto.setBody("PostBody" + i);
			samplePosts.add(dto);
		}
	}

	@Test
	@Order(1)
	void testCreatePost() throws Exception {
		for(PostDTO postDTO : samplePosts) {
			String requestBody = objectMapper.writeValueAsString(postDTO);
			final ResultActions result = mockMvc.perform(post("/posts")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody));

				result.andExpect(status().isCreated())
				.andExpect(jsonPath("$.title").value(postDTO.getTitle()))
				.andExpect(jsonPath("$.body").value(postDTO.getBody()))
				.andExpect(jsonPath("$.writer").value(user.getId()))
				.andDo(print());
		}
	}

	@Test
	@Order(2)
	void testGetAllPosts() throws Exception {
		mockMvc.perform(get("/posts"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(samplePosts.size()))
			.andExpect(jsonPath("$[0].title").value(samplePosts.get(0).getTitle()))
			.andDo(print());
	}

	@Test
	@Order(3)
	void testGetPostById() throws Exception {
		List<PostDTO> posts = postService.getAllPosts();
		Long postId = posts.get(0).getId();
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
		PostDTO updatePost = postService.getAllPosts().get(0);
		Long postId = updatePost.getId();
		updatePost.setTitle("Updated Title");
		updatePost.setBody("Updated Body");
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
		PostDTO deletePost = postService.getAllPosts().get(0);
		Long postId = deletePost.getId();

		mockMvc.perform(delete("/posts/" + postId))
			.andExpect(status().isNoContent())
			.andDo(print());
	}
}
