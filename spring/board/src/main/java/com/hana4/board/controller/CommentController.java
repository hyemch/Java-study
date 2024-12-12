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

import com.hana4.board.dto.CommentDTO;
import com.hana4.board.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@GetMapping
	public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Long postId) {
		return ResponseEntity.ok(commentService.getComments(postId));
	}

	@PostMapping
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Long postId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(commentDTO, postId));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO,
		@PathVariable Long postId) {
		return ResponseEntity.ok(commentService.updateComment(commentDTO, postId, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable Long id, @PathVariable Long postId) {
		commentService.deleteComment(postId, id);
		return ResponseEntity.noContent().build();
	}
}
