package com.hana4.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
	private Long id;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
	private String title;
	private String writer; //User id
	private String body;

}
