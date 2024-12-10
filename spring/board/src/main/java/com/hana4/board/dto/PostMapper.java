package com.hana4.board.dto;

import org.springframework.stereotype.Component;

import com.hana4.board.entity.Post;
import com.hana4.board.entity.User;

@Component
public class PostMapper {
	public static PostDTO toDTO(Post post) {
		return PostDTO.builder().id(post.getId()).createAt(post.getCreateAt()).updateAt(post.getUpdateAt()).title(post.getTitle()).writer(post.getWriter().getId()).body(post.getBody()).build();
	}

	public static Post toEntity(PostDTO postDTO, User writer) {
		return new Post(postDTO.getId(), postDTO.getCreateAt(), postDTO.getUpdateAt(), postDTO.getTitle(),writer,
			postDTO.getBody());
	}
}
