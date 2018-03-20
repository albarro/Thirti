package com.uniovi.services;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Friend;
import com.uniovi.entities.User;
import com.uniovi.repository.FriendRepository;

@Service
public class FriendService {


	@Autowired
	private FriendRepository friendRepository;

	public Page<Friend> getFriendsForUser(Pageable pageable, User user) {
		Page<Friend> marks = new PageImpl<Friend>(new LinkedList<Friend>());
		marks = friendRepository.findAllByUser(pageable, user);
		return marks;
	}

	public void addFriendRelationship(User friend, User friendOf) {
		addFriend(new Friend(friend, friendOf));
		addFriend(new Friend(friendOf, friend));
	}
	
	private void addFriend(Friend friend) {
		friendRepository.save(friend);

	}

}
