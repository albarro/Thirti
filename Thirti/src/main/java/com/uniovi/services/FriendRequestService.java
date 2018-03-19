package com.uniovi.services;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.FriendRequest;
import com.uniovi.entities.User;
import com.uniovi.repository.FriendRequestRepository;

@Service
public class FriendRequestService {


	@Autowired
	private FriendRequestRepository friendRequestRepository;

	public Page<FriendRequest> getRequestsForUser(Pageable pageable, User user) {
		Page<FriendRequest> marks = new PageImpl<FriendRequest>(new LinkedList<FriendRequest>());
		marks = friendRequestRepository.findAllByUser(pageable, user);
		return marks;
	}

	public Page<FriendRequest> getFriendRequest(Pageable pageable) {
		Page<FriendRequest> marks = friendRequestRepository.findAll(pageable);
		return marks;
	}

	public void setRequestAccepted(Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String dni = auth.getName();

		FriendRequest request = friendRequestRepository.findOne(id);

		if (request.getUserRecived().getEmail().equals(dni)) {
			friendRequestRepository.updateResend(true, id);
		}
	}


	public void addFriendRequest(FriendRequest mark) {
		// Si en Id es null le asignamos el ultimo + 1 de la lista
		friendRequestRepository.save(mark);

	}

	public void deleteFriendRequest(Long id) {
		friendRequestRepository.delete(id);
	}
}
