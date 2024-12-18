package com.hana4.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
	@UniqueConstraint(
		name = "uniq_Code_codename_info",
		columnNames = {"codeName"}
	)})
public class Code extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 32)
	private String codeName;

	@OneToOne(mappedBy = "code", cascade = CascadeType.ALL)
	private CodeInfo codeInfo;

	@ToString.Exclude
	@OneToMany(mappedBy = "code", fetch = FetchType.EAGER)
	private List<SubCode> subcodes;

	@ManyToMany(mappedBy = "useCodes", fetch = FetchType.EAGER) //lazy 하면 테스트 에러
	private List<User> codeUsers = new ArrayList<>();

	public void addUser(User user) {
		this.codeUsers.add(user);
	}
}
