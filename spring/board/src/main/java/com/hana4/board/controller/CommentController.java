package com.hana4.board.controller;
import java.util.List;

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
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@PostMapping
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
		return ResponseEntity.ok(commentService.createComment(commentDTO));
	}

	@GetMapping
	public ResponseEntity<List<CommentDTO>> getAllComments() {
		return ResponseEntity.ok(commentService.getAllComments());
	}

	@GetMapping("{id}")
	public ResponseEntity<CommentDTO> getComment(@PathVariable Long id) {
		return ResponseEntity.ok(commentService.getCommentById(id));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
		return ResponseEntity.ok(commentService.updateComment(commentDTO, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
		commentService.deleteComment(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{postId}")
	public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable Long postId) {
		return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
	}

	@GetMapping("/{writerId}")
	public ResponseEntity<List<CommentDTO>> getCommentsByWriterId(@PathVariable String writerId) {
		return ResponseEntity.ok(commentService.getCommentsByUserId(writerId));
	}
}
