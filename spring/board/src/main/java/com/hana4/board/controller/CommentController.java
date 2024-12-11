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
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	//목록
	@GetMapping
	public ResponseEntity<List<CommentDTO>> getAllComments() {
		return ResponseEntity.ok(commentService.getAllComments());
	}

	//쓰기
	@PostMapping
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(commentDTO));
	}

	//수정
	@PatchMapping("/{id}")
	public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
		return ResponseEntity.ok(commentService.updateComment(commentDTO, id));
	}

	//삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
		commentService.deleteComment(id);
		return ResponseEntity.noContent().build();
	}

	//--------------------------------------------------
	//댓글 상세
	@GetMapping("/{id}")
	public ResponseEntity<CommentDTO> getComment(@PathVariable Long id) {
		return ResponseEntity.ok(commentService.getCommentById(id));
	}

	//포스트별 댓글
	@GetMapping("/{postId}/comments")
	public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable Long postId) {
		return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
	}

	//사용자별 댓글
	@GetMapping("/{writerId}/comments")
	public ResponseEntity<List<CommentDTO>> getCommentsByWriterId(@PathVariable String writerId) {
		return ResponseEntity.ok(commentService.getCommentsByUserId(writerId));
	}
}
