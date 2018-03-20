package com.uniovi.services;

import java.util.*;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repository.UsersRepository;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void init() {
	}

	public Page<User> getUsers(Pageable pageable) {
		Page<User> users =	usersRepository.findAll(pageable);
		return users;
	}

	public User getUser(Long id) {
		return usersRepository.findOne(id);
	}
	
	public User getUserByEmail(String email) {
		return usersRepository.findByEmail(email);
	}
	

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	public void deleteUser(Long id) {
		usersRepository.delete(id);
	}

	public Page<User> searchUserByEmailAndName(Pageable pageable, String searchText) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		searchText = "%"+searchText+"%";
		users = usersRepository.
					searchByEmailAndName(pageable, searchText);

		return users;
	}

	
}
