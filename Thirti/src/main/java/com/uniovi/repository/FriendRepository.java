package com.uniovi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Friend;
import com.uniovi.entities.User;

public interface FriendRepository extends CrudRepository<Friend, Long> {

	@Query("SELECT r FROM Friend r WHERE r.friend = ?1 ORDER BY r.id ASC ")
	Page<Friend> findAllByUser(Pageable pageable, User user);
	


}