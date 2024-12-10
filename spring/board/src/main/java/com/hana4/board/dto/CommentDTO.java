package com.hana4.board.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentDTO {
	private Long id;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
	private String writer; //User id
	private Long post; //Post id
	private String body;
}
