package com.hana4.board.Repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hana4.board.entity.Post;
import com.hana4.board.entity.User;
import com.hana4.board.repository.PostRepository;
import com.hana4.board.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class PostRepositoryTest {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	private User user;

	@BeforeEach
	void setUp() {
		postRepository.deleteAll();
		user = userRepository.findAll().get(0);
		Post post1 = new Post();
		post1.setTitle("Title 1");
		post1.setBody("Body 1");
		post1.setWriter(user);
		postRepository.save(post1);

		Post post2 = new Post();
		post2.setTitle("Title 2");
		post2.setBody("Body 2");
		post2.setWriter(user);
		postRepository.save(post2);
	}

	@Test
	@Order(1)
	void testCreatePost() {
		for (int i = 0; i < 3; i++) {

			Post post = new Post();
			post.setWriter(user);
			post.setTitle("Title Test" + i);
			post.setBody("Body Test" + i);
			Post savePost = postRepository.save(post);

			assertThat(savePost.getId()).isNotNull();
			assertThat(savePost.getTitle()).isEqualTo("Title Test" + i);
			assertThat(savePost.getWriter().getId()).isEqualTo(user.getId());
		}
	}

	@Test
	@Order(2)
	void testGetPostById() {
		Post post = postRepository.findAll().get(1);
		Post savePost = postRepository.save(post);

		Post getPost = postRepository.findById(savePost.getId()).orElseThrow();
		assertThat(getPost.getTitle()).isEqualTo(post.getTitle());
		assertThat(getPost.getBody()).isEqualTo(post.getBody());
	}

	@Test
	@Order(3)
	void testGetAllPosts() {
		List<Post> posts = postRepository.findAll();
		assertThat(posts).isNotEmpty();
		assertThat(posts).hasSize(2);
		assertThat(posts.get(0).getTitle()).isEqualTo("Title 1");
	}

	@Test
	@Order(4)
	void testUpdatePost() throws InterruptedException {
		Post post = postRepository.findAll().get(1);
		LocalDateTime oldUpdateAt = post.getUpdateAt();
		post.setTitle("Updated Title!!");
		post.setBody("Updated Body!!");

		Thread.sleep(3000);
		Post savePost = postRepository.saveAndFlush(post);
		assertThat(savePost.getTitle()).isEqualTo("Updated Title!!");
		assertThat(savePost.getBody()).isEqualTo("Updated Body!!");
		assertThat(savePost.getUpdateAt()).isAfter(oldUpdateAt);
	}

	@Test
	@Order(5)
	void testDeletePost() {
		List<Post> beforePosts = postRepository.findAll();
		Post post = postRepository.findAll().get(0);
		postRepository.delete(post);
		boolean exists = postRepository.existsById(post.getId());
		assertThat(exists).isFalse();

		List<Post> afterPosts = postRepository.findAll();
		assertThat(afterPosts.size()).isLessThan(beforePosts.size());
	}


}
