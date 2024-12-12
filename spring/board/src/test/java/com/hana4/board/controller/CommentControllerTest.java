package com.hana4.board.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class CommentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired CommentRepository commentRepository;

	@Autowired UserRepository userRepository;

	@Autowired PostRepository postRepository;

	private User writer;
	private Post post;
	private Post post2;
	private List<CommentDTO> sampleComments;

	@BeforeEach
	void setUp() {
		commentRepository.deleteAll();
		postRepository.deleteAll();

		sampleComments = new ArrayList<>();

		writer = userRepository.findAll().get(0);
		post = new Post();
		post.setWriter(writer);
		post.setTitle("Test Comment");
		post.setBody("This is a sample Post with Comment");
		postRepository.save(post);

		post2 = new Post();
		post2.setWriter(writer);
		post2.setTitle("Test Comment2");
		post2.setBody("This is a sample Post with Comment2");
		postRepository.save(post2);

		for (int i = 0; i < 4; i++) {
			Comment comment = new Comment();
			comment.setWriter(writer);
			comment.setPost(post);
			comment.setBody("Comment of Post " + i);
			commentRepository.save(comment);
			sampleComments.add(CommentMapper.toDTO(comment));
		}

		for (int i = 0; i < 2; i++) {
			Comment comment = new Comment();
			comment.setWriter(writer);
			comment.setPost(post2);
			comment.setBody("Comment of Post2" + i);
			commentRepository.save(comment);

			sampleComments.add(CommentMapper.toDTO(comment));

		}
	}

	@Test
	@Order(1)
	void testGetComments() throws Exception {
		Long postId = post.getId();
		int commentCounts =
			sampleComments.stream().filter(comment -> comment.getPost().equals(postId)).toList().size();
		mockMvc.perform(get("/posts/{postId}/comments", postId))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(commentCounts))
			.andDo(print());
	}

	@Test
	@Order(2)
	void testCreateComment() throws Exception {
		CommentDTO dto = new CommentDTO();
		dto.setPost(post.getId());
		dto.setWriter(writer.getId());
		dto.setBody("This is a sample comment");

		String requestBody = objectMapper.writeValueAsString(dto);
		mockMvc.perform(post("/posts/{postId}/comments", post.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty())
				.andExpect(jsonPath("$.body").value(dto.getBody()))
				.andDo(print());
	}

	@Test
	@Order(3)
	void testUpdateComment() throws Exception {
		CommentDTO updateComment = sampleComments.get(0);
		final String updateBody = "This is updated comment";
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
		CommentDTO deleteComment = sampleComments.get(0);
		int sampleCommentSize = sampleComments.stream().filter(comment -> comment.getPost().equals(sampleComments.get(0).getPost())).toList().size();
		mockMvc.perform(delete("/posts/{post_id}/comments/{id}", deleteComment.getPost(),deleteComment.getId()))
			.andExpect(status().isNoContent())
			.andDo(print());
	}
}
