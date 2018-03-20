package com.uniovi.entities;

import java.util.HashSet;
import java.util.Set; //A collection that contains no duplicate elements

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String email;
	private String name;
	private String role;

	private String password;
	@Transient // Specifies that the property or field is not persistent.
	private String passwordConfirm;

	@OneToMany(mappedBy = "userRecived", cascade = CascadeType.ALL)
	private Set<FriendRequest> friendsRequest;

	 @OneToMany(mappedBy = "friendOf", cascade = CascadeType.ALL)
	private Set<Friend> friends;

	public User(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public void setFriendRequest(Set<FriendRequest> friendsRequest) {
		this.friendsRequest = friendsRequest;
	}

	public Set<FriendRequest>  getFriendRequest() {
		return friendsRequest;
	}
	
	public Set<Friend> getFriends() {
		return friends;
	}

	public void setFriends(Set<Friend> friends) {
		this.friends = friends;
	}
	
}