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
import com.hana4.board.dto.CommentDTO;
import com.hana4.board.dto.PostDTO;
import com.hana4.board.dto.PostMapper;
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
public class CommentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private final List<CommentDTO> sampleComments = new ArrayList<>();
	@Autowired
	private CommentService commentService;

	@BeforeAll
	void setUp(@Autowired CommentRepository commentRepository,
	@Autowired UserRepository userRepository, @Autowired PostRepository postRepository) {
		commentRepository.deleteAll();
		postRepository.deleteAll();
		User user = userRepository.findAll().get(0);

		PostDTO dto = new PostDTO();
		dto.setWriter(user.getId());
		dto.setTitle("Post with comments");
		dto.setBody("PostBody with comments");
		Post post = postRepository.save(PostMapper.toEntity(dto, user));

		for (int i = 0; i < 4; i++) {
			CommentDTO comment = new CommentDTO();
			comment.setWriter(user.getId());
			comment.setPost(post.getId());
			comment.setBody("Comment of Post " + i);
			sampleComments.add(comment);
		}
	}

	@Test
	@Order(1)
	void testCreateComment() throws Exception {
		for (CommentDTO comment : sampleComments) {
			String requestBody = objectMapper.writeValueAsString(comment);
			final ResultActions result =
		mockMvc.perform(post("/posts/{postId}/comments", comment.getPost())
			.contentType(MediaType.APPLICATION_JSON)
			.content(requestBody));
		result.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").isNotEmpty())
			.andExpect(jsonPath("$.body").value(comment.getBody()))
			.andDo(print());
		}
	}

	@Test
	@Order(2)
	void testGetComments() throws Exception {
		Long postId = sampleComments.get(0).getPost();
		List<CommentDTO> comments = commentService.getComments(postId);
		int commentCounts = sampleComments.size();
		mockMvc.perform(get("/posts/{postId}/comments", postId))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(commentCounts))
			.andExpect(jsonPath("$[0].id").value(comments.get(0).getId()))
			.andExpect(jsonPath("$[0].post").value(postId))
			.andDo(print());
	}

	@Test
	@Order(3)
	void testUpdateComment() throws Exception {
		CommentDTO updateComment = commentService.getComments(sampleComments.get(0).getPost()).get(0);
		final String updateBody = "This is a new new new updated comment!";
		updateComment.setBody(updateBody);
		String requestBody = objectMapper.writeValueAsString(updateComment);

		mockMvc.perform(patch("/posts/{postId}/comments/{id}", updateComment.getPost(), updateComment.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.body").value(updateBody))
			.andDo(print());
	}

	@Test
	@Order(4)
	void testDeleteComment() throws Exception {
		CommentDTO deleteComment = commentService.getComments(sampleComments.get(0).getPost()).get(0);
		Long postId = deleteComment.getPost();
		Long commentId = deleteComment.getId();

		mockMvc.perform(delete("/posts/{post_id}/comments/{id}", postId,commentId))
			.andExpect(status().isNoContent())
			.andDo(print());
	}
}
