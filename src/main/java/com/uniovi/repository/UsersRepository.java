package com.uniovi.repository;

import com.uniovi.entities.*;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {

	User findByEmail(String dni);
	
}