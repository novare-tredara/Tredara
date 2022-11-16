package com.novare.tredara.dtos;

import com.novare.tredara.models.User;

public class UserDTO {

	private Long id;
	private String email;
	private String password;
	private String name;
	private Integer type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public static UserDTO build(User user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setName(user.getFullName());
		dto.setPassword(user.getPassword());
		switch (user.getRole().getName()) {
			case ROLE_ADMIN -> dto.setType(1);
			case ROLE_CUSTOMER -> dto.setType(2);
			default -> throw new IllegalArgumentException("Unexpected value: " + user.getRole().getName());
		}
		return dto;
	}

	public static User buildModel(UserDTO dto) {
		return new User(dto.getName(), dto.getEmail(), dto.getPassword());
	}
}
