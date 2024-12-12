package com.hana4.board.Repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hana4.board.entity.Comment;
import com.hana4.board.entity.Post;
import com.hana4.board.entity.User;
import com.hana4.board.repository.CommentRepository;
import com.hana4.board.repository.PostRepository;
import com.hana4.board.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class CommentRepositoryTest {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	private User user;
	private List<Post> posts;;

	@BeforeEach
	void setUp() {
		commentRepository.deleteAll();
		postRepository.deleteAll();

		user = userRepository.findAll().get(0);
		posts = new ArrayList<>();
		for (int i = 0; i < 2; i++)
		{
			Post post = new Post();
			post.setWriter(user);
			post.setTitle("Title Comment" + i);
			post.setBody("Body Comment" + i);
			postRepository.save(post);
			posts.add(post);

			Comment comment = new Comment();
			comment.setWriter(user);
			comment.setPost(posts.get(i));
			comment.setBody("Test Comment" + i);
			commentRepository.save(comment);
		}
	}

	@Test
	@Order(1)
	void testCreateComment() {
			Comment comment = new Comment();
			comment.setWriter(user);
			comment.setPost(posts.get(0));
			comment.setBody("Create Comment");
			Comment savedComment = commentRepository.save(comment);

			assertThat(savedComment.getId()).isNotNull();
			assertThat(savedComment.getBody()).isEqualTo("Create Comment");
			assertThat(savedComment.getPost().getId()).isEqualTo(posts.get(0).getId());
		assertThat(commentRepository.findAll().size()).isEqualTo(3);
	}

	@Test
	@Order(2)
	void testGetComments() {

		List<Comment> foundComment1 = commentRepository.findByPostId(posts.get(0).getId());
		assertThat(foundComment1.size()).isEqualTo(1);
		assertThat(foundComment1.get(0).getBody()).isEqualTo("Test Comment0");

		List<Comment> foundComment2 = commentRepository.findByPostId(postRepository.findAll().get(1).getId());
		assertThat(foundComment2.size()).isEqualTo(1);
		assertThat(foundComment2.get(0).getBody()).isEqualTo("Test Comment1");
	}

	@Test
	@Order(3)
	void updateCommentTest() throws InterruptedException {

		Comment comment = commentRepository.findAll().get(0);
		LocalDateTime oldUpdateAt = comment.getUpdateAt();

		Thread.sleep(3000);
		comment.setBody("*****Updated Comment*****");

		Comment updatedComment = commentRepository.saveAndFlush(comment);
		assertThat(updatedComment.getBody()).isEqualTo("*****Updated Comment*****");
		assertThat(updatedComment.getUpdateAt()).isAfter(oldUpdateAt);
	}

	@Test
	@Order(4)
	void deleteCommentTest() {
		Comment comment = commentRepository.findAll().get(1);
		int oldCommentSize = commentRepository.findAll().size();
		commentRepository.delete(comment);
		boolean exists = commentRepository.existsById(comment.getId());
		assertThat(exists).isFalse();

		assertThat(commentRepository.findAll().size()).isEqualTo(oldCommentSize - 1);
	}
}
