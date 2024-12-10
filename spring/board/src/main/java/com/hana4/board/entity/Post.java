package com.hana4.board.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Post")
@Getter
@Setter
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createAt;

	@Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime updateAt;

	@Column(length = 255, nullable = false)
	private String title;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "writer", nullable = false)
	// @Column(length = 36, nullable = false)
	private User writer;

	@Column(columnDefinition = "TEXT", nullable = false)

	private String body;

	// public Post(Long id, LocalDateTime createAt, LocalDateTime updateAt, String title, User writer, String body) {
	// 	this.id = id;
	// 	this.createAt = LocalDateTime.now();
	// 	this.updateAt = LocalDateTime.now();
	// 	this.title = title;
	// 	this.writer = writer;
	// 	this.body = body;
	// }
}
