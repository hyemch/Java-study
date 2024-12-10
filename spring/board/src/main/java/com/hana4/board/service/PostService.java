package com.hana4.board.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hana4.board.dto.PostDTO;
import com.hana4.board.dto.PostMapper;
import com.hana4.board.entity.Post;
import com.hana4.board.entity.User;
import com.hana4.board.repository.PostRepository;
import com.hana4.board.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
	private final UserRepository userRepository;

	public PostDTO createPost(PostDTO dto) {
		User writer = userRepository.findById(dto.getWriter()).orElseThrow(() -> new IllegalArgumentException("User does not exist"));
		Post post = PostMapper.toEntity(dto, writer);
		return PostMapper.toDTO(postRepository.save(post));
	}

	public PostDTO getPostById(Long postId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));

		return PostMapper.toDTO(post);
	}

	public List<PostDTO> getAllPosts() {
		return postRepository.findAll().stream().map(PostMapper::toDTO).collect(Collectors.toList());
	}

	public PostDTO updatePost(PostDTO dto, Long postId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));

		post.setTitle(dto.getTitle());
		post.setBody(dto.getBody());
		post.setUpdateAt(LocalDateTime.now());
		return PostMapper.toDTO(postRepository.save(post));
	}

	public void deletePost(Long postId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
		postRepository.delete(post);
	}

}
