package com.hana4.board.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
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
	@Column(name = "id", nullable = false, columnDefinition = "BIGINT UNSIGNED")
	private Long id;

	@CreationTimestamp
	@Column(nullable = false, updatable = false, columnDefinition = "timestamp")
	@ColumnDefault("CURRENT_TIMESTAMP")
	@org.hibernate.annotations.Comment("등록일시")
	private LocalDateTime createAt;

	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "timestamp")
	@ColumnDefault("CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@org.hibernate.annotations.Comment("수정일시")
	private LocalDateTime updateAt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="post", nullable=false, foreignKey = @ForeignKey(name = "comment_post_foreign"))
	private Post post;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "writer", nullable=false, foreignKey = @ForeignKey(name = "comment_writer_foreign"))
	@org.hibernate.annotations.Comment("작성자 ID")
	private User writer;

	@Column(length = 500, nullable = false)
	private String body;
}
