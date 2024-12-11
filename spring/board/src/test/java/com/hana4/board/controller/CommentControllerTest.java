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
import com.hana4.board.dto.CommentDTO;
import com.hana4.board.dto.CommentMapper;
import com.hana4.board.entity.Comment;
import com.hana4.board.entity.Post;
import com.hana4.board.entity.User;
import com.hana4.board.repository.CommentRepository;
import com.hana4.board.repository.PostRepository;
import com.hana4.board.repository.UserRepository;
import com.hana4.board.service.CommentService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class CommentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CommentService commentService;


	private User writer;
	private Post post;
	private final List<CommentDTO> sampleComments = new ArrayList<>();

	@BeforeEach
	void setUp(@Autowired CommentRepository commentRepository,
	@Autowired UserRepository userRepository, @Autowired PostRepository postRepository) {
		sampleComments.clear();
		writer = userRepository.findAll().get(0);
		post = new Post();
		post.setId(1L);
		post.setWriter(writer);
		post.setTitle("Test Comment");
		post.setBody("This is a sample Post with Comment");
		postRepository.save(post);

		for (int i = 0; i < 3; i++) {
			CommentDTO dto = new CommentDTO();
			dto.setId(sampleComments.size() + post.getId());
			dto.setPost(post.getId());
			dto.setWriter(writer.getId());
			dto.setBody("This is a sample comment" + i);
			Comment saveComment = commentRepository.save(CommentMapper.toEntity(dto, post, writer));
			dto.setId(saveComment.getId());
			sampleComments.add(dto);
		}
		System.out.println("Posts in DB: " + postRepository.count());
		System.out.println("Comments in DB: " + commentRepository.count());
	}

	@Test
	@Order(1)
	void testGetAllComments() throws Exception {

		System.out.println(objectMapper.writeValueAsString(sampleComments));
		System.out.println("sampleComments = " + sampleComments.size());
		final int commentCount = commentService.getAllComments().size();
		mockMvc.perform(get("/comments"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(sampleComments.size()))
			.andExpect(jsonPath("$.length()").value(commentCount))
			.andDo(print());
	}


	@Test
	@Order(2)
	void testCreateComment() throws Exception {
		CommentDTO dto = new CommentDTO();
		dto.setId(post.getId());
		dto.setPost(post.getId());
		dto.setWriter(writer.getId());
		dto.setBody("This is a sample comment " + dto.getId());

		String requestBody = objectMapper.writeValueAsString(dto);
		mockMvc.perform(post("/comments")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty())
				.andExpect(jsonPath("$.writer").value(dto.getWriter()))
				.andExpect(jsonPath("$.post").value(dto.getPost()))
				.andExpect(jsonPath("$.body").value(dto.getBody()))
				.andDo(print());
	}

	@Test
	@Order(3)
	void testUpdateComment() throws Exception {
		CommentDTO updateComment = sampleComments.get(0);
		final String updateBody = "This is updated comment";
		updateComment.setBody(updateBody);

		final String url = "/comments/" + updateComment.getId();
		String requestBody = objectMapper.writeValueAsString(updateComment);

		mockMvc.perform(patch("/comments/{id}", updateComment.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").isNotEmpty())
			.andExpect(jsonPath("$.body").value(updateBody))
			.andDo(print());
	}

	@Test
	@Order(4)
	void testDeleteComment() throws Exception {
		CommentDTO deleteComment = sampleComments.get(0);
		int sampleCommentSize = sampleComments.size();
		mockMvc.perform(delete("/comments/{id}", deleteComment.getId()))
			.andExpect(status().isNoContent())
			.andDo(print());

		// mockMvc.perform(get("/comments"))
		// 	.andExpect(status().isOk())
		// 	.andExpect(jsonPath("$.length()").value(sampleCommentSize - 1)) // 삭제 후 댓글 수 확인
		// 	.andDo(print());

		List<CommentDTO> remainingComments = commentService.getAllComments();
		assertThat(remainingComments.size()).isEqualTo(sampleCommentSize - 1);

	}
}
