package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;

//	@PostConstruct
//	public void init() {
//		User user1 = new User("prueba@hola.es", "Pedro");
//		user1.setPassword("123456");
//		User user2 = new User("prueba1@hola.es", "Lucas");
//		user2.setPassword("123456");
//		User user3 = new User("prueba2@hola.es", "María");
//		user3.setPassword("123456");
//		User user4 = new User("prueba3@hola.es", "Marta");
//		user4.setPassword("123456");
//		User user5 = new User("prueba4@hola.es", "Pelayo");
//		user5.setPassword("123456");
//		User user6 = new User("prueba5@hola.es", "Edward");
//		user6.setPassword("123456");
//		
//		usersService.addUser(user1);
//		usersService.addUser(user2);
//		usersService.addUser(user3);
//		usersService.addUser(user4);
//		usersService.addUser(user5);
//		usersService.addUser(user6);
//	}
}
