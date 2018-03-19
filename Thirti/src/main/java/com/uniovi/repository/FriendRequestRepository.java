package com.uniovi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.FriendRequest;
import com.uniovi.entities.User;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, Long> {

	@Query("SELECT r FROM FriendRequest r WHERE r.userRecived = ?1 ORDER BY r.id ASC ")
	Page<FriendRequest> findAllByUser(Pageable pageable, User user);
	
	Page<FriendRequest> findAll(Pageable pageable); 
	

	@Modifying
	@Transactional
	@Query("UPDATE FriendRequest SET accepted = ?1 WHERE id = ?2")
	void updateResend(Boolean resend, Long id);

}
