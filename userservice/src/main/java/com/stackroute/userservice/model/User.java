package com.stackroute.userservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.ComponentScan;

@Entity
@ComponentScan("com.stackroute.accountmanager.model")
@Table(name = "LOGIN_USER")
public class User {

	@Id
	@Column(length = 35)
	private String username;
	@Column(length = 35)
	private String firstName;
	@Column(length = 35)
	private String lastName;
	@Column(length = 35)
	private String password;

	public User() {
		super();
	}

	public User(String username, String firstName, String lastName, String password, Date userAddedDate) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + "]";
	}
}
