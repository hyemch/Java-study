package com.hana4.board.dto;

import org.springframework.stereotype.Component;

import com.hana4.board.entity.Comment;
import com.hana4.board.entity.Post;
import com.hana4.board.entity.User;

@Component
public class CommentMapper {
	public static CommentDTO toDTO(Comment comment) {
		return CommentDTO.builder().
			id(comment.getId()).
			createAt(comment.getCreateAt()).
			updateAt(comment.getUpdateAt()).
			post(comment.getPost().getId()).
			writer(comment.getWriter().getId()).
			body(comment.getBody()).build();
	}

	public static Comment toEntity(CommentDTO commentDTO, Post post, User writer) {
		return new Comment(
			commentDTO.getId(),
			commentDTO.getCreateAt(),
			commentDTO.getUpdateAt(),
			post,
			writer,
			commentDTO.getBody()
		);
	}
}
