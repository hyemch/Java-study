package com.hana4.library.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
	private int bno;
	private String title;
	private String author;
	private String publisher;
	private String description;
	private String isbn;
	private boolean availability;
	private String borrowerId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}
