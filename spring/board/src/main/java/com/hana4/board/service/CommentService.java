package com.hana4.board.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hana4.board.dto.CommentDTO;
import com.hana4.board.dto.CommentMapper;
import com.hana4.board.entity.Comment;
import com.hana4.board.entity.Post;
import com.hana4.board.entity.User;
import com.hana4.board.repository.CommentRepository;
import com.hana4.board.repository.PostRepository;
import com.hana4.board.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;

	public CommentDTO createComment(CommentDTO dto, Long postId) {
		User writer = userRepository.findById(dto.getWriter()).orElseThrow(() -> new IllegalArgumentException("User "
			+ "dose not exist"));
		Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
		Comment comment = CommentMapper.toEntity(dto, post, writer);
		Comment savedComment = commentRepository.save(comment);
		return CommentMapper.toDTO(savedComment);
	}

	public List<CommentDTO> getComments(Long postId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
		return commentRepository.findByPostId(post.getId()).stream().map(CommentMapper::toDTO).collect(Collectors.toList());
	}

	public CommentDTO updateComment(CommentDTO dto, Long postId, Long commentId) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Comment not found"));

		comment.setBody(dto.getBody());
		comment.setUpdateAt(LocalDateTime.now());
		return CommentMapper.toDTO(commentRepository.save(comment));
	}

	public void deleteComment(Long postId, Long commentId) {
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
		commentRepository.delete(comment);
	}

	public CommentDTO getCommentById(Long commentId) {
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Comment not "
			+ "found"));

		return CommentMapper.toDTO(comment);
	}

	// public List<CommentDTO> getCommentsByPostId(Long postId) {
	// 	Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
	//
	// 	return commentRepository.findByPostId(post.getId()).stream().map(CommentMapper::toDTO).toList();
	// }

	public List<CommentDTO> getCommentsByUserId(String userId) {
		User writer = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

		return commentRepository.findByWriterId(writer.getId()).stream().map(CommentMapper::toDTO).toList();
	}
}
