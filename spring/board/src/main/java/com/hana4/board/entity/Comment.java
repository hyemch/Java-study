package com.hana4.board.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name="Comment")
@Getter
@Setter
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	// @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Column(nullable = false, updatable = false, columnDefinition = "timestamp")
	@ColumnDefault("CURRENT_TIMESTAMP")
	private LocalDateTime createAt;

	// @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "timestamp")
	@ColumnDefault("CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime updateAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="post", nullable=false)
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "writer", nullable=false)
	// @Column(length = 36, nullable = false)
	private User writer;

	@Column(length = 500, nullable = false)
	private String body;

	// public Comment(Long id, LocalDateTime createAt, LocalDateTime updateAt, Post post, User writer, String body) {
	// 	this.id = id;
	// 	this.createAt = createAt;
	// 	this.updateAt = updateAt;
	// 	this.post = post;
	// 	this.writer = writer;
	// 	this.body = body;
	// }
}
