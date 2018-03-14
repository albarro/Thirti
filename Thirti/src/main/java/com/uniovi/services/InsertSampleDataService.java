package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;

	@PostConstruct
	public void init() {
		User user1 = new User("prueba@email.com", "Pedro");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		User user2 = new User("prueba1@email.com", "Lucas");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		User user3 = new User("prueba2@email.com", "María");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		User user4 = new User("prueba3@email.com", "Marta");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		User user5 = new User("prueba4@email.com", "Pelayo");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		User user6 = new User("prueba5@email.com", "Edward");
		user6.setPassword("123456");
		user6.setRole(rolesService.getRoles()[1]);
		
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
	}
}
