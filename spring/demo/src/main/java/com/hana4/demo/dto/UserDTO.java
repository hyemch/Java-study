package com.hana4.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * User user = UserDTO.buildr()
 *
 * */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "age")
@EqualsAndHashCode
@Builder
public class UserDTO {
	//domain(entity)과 dto 분리
	private Long id;
	private String name;
	private short age;

	// @Singular("user")
	// private List<User> list;

}
