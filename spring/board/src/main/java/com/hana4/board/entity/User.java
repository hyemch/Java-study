package com.hana4.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "User", uniqueConstraints =  @UniqueConstraint(columnNames = "name"))
@Getter
@Setter
public class User {
	@Id
	@Column(length = 36, nullable = false)
	private String id;

	@Column(length = 31, nullable = false, unique = true)
	private String name;

	@Column(length = 255)
	private String email;
}
