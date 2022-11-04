package com.novare.tredara.models;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.novare.tredara.utils.DateUtil;

@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(name = "EMAIL", columnNames = "EMAIL") })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "EMAIL")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide a valid Email address")
	private String email;

	@Column(name = "FULL_NAME")
	@NotEmpty(message = "*Please provide a valid Full Name")
	private String fullName;

	@Column(name = "PASSWORD")
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	private String password;

	@Column(name = "STATUS")
	private int status;

	@Column(name = "CREATED_ON")
	private Date createdOn;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(foreignKey = @ForeignKey(name = "USER_ROLE_ID"), name = "ROLE_ID", referencedColumnName = "ID")
	private Role role;

	public User() {
		createdOn = DateUtil.toDate(LocalDateTime.now());
	}

	public User(Long id, String fullName, String email, Role role) {
		this();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.role = role;
	}

	public User(String fullName, String email, String password) {
		this();
		this.fullName = fullName;
		this.email = email;
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

}