package com.hana4.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hana4.board.dto.PostDTO;
import com.hana4.board.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;

	@PostMapping
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(dto));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<PostDTO> updatePost(@PathVariable("id") Long id, @RequestBody PostDTO dto) {
		return ResponseEntity.ok(postService.updatePost(dto, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PostDTO> deletePost(@PathVariable("id") Long id) {
		postService.deletePost(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<PostDTO>> getAllPosts() {
		return ResponseEntity.ok(postService.getAllPosts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(postService.getPostById(id));
	}

}
