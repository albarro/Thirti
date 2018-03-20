package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Friend {
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "friend_id")
	private User friend;

	@ManyToOne
	@JoinColumn(name = "friendOf_id")
	private User friendOf;

	public Friend(User friend, User friendOf) {
		super();
		this.setFriend(friend);
		this.setFriendOf(friendOf);
	}

	public Friend() {
	}

	
	public User getFriendOf() {
		return friendOf;
	}

	public void setFriendOf(User friendOf) {
		this.friendOf = friendOf;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}
}
