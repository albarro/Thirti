package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Friend;
import com.uniovi.entities.FriendRequest;
import com.uniovi.entities.User;
import com.uniovi.services.FriendRequestService;
import com.uniovi.services.FriendService;
import com.uniovi.services.UsersService;

@Controller
public class RequestController {
	
	@Autowired 
	private UsersService usersService;
	
	@Autowired 
	private FriendRequestService friendRequestService;
	
	@Autowired
	private FriendService friendService;
	
	@RequestMapping("/request/list" )
	public String getListadoRequest(Model model, Pageable pageable, Principal principal,
			@RequestParam(value = "", required=false) String searchtext){
		String email = principal.getName(); // Email es el name de la autenticación
		User user = usersService.getUserByEmail(email);
		Page<FriendRequest> request = new PageImpl<FriendRequest>(new LinkedList<FriendRequest>());
		request = friendRequestService.getRequestsForUser(pageable, user);
		model.addAttribute("requestList", request.getContent());
		model.addAttribute("page", request);
		return "request/listRequest";
	}
	
	@RequestMapping(value="/request/add/{id}")
	public String addFriendRequest(Model model, Pageable pageable, Principal principal, @PathVariable  Long id){
		String email = principal.getName(); // Email es el name de la autenticación
		User activeUser = usersService.getUserByEmail(email);
		User reciverUser = usersService.getUser(id);
		friendRequestService.addFriendRequest(new FriendRequest(activeUser, reciverUser));
		model.addAttribute("user", reciverUser);
		return "request/add";
	}
	
	@RequestMapping(value="/request/accept/{id}")
	public String addFriend(Model model, Pageable pageable, Principal principal, @PathVariable  Long id){
		String email = principal.getName(); // Email es el name de la autenticación
		User activeUser = usersService.getUserByEmail(email);
		FriendRequest fr = friendRequestService.getFriendRequest(id);
		User reciverUser = usersService.getUser(fr.getSendId());
		friendRequestService.setRequestAccepted(id);
		
		friendService.addFriendRelationship(activeUser, reciverUser);
		
		Page<FriendRequest> request = new PageImpl<FriendRequest>(new LinkedList<FriendRequest>());
		request = friendRequestService.getRequestsForUser(pageable, activeUser);
		model.addAttribute("requestList", request.getContent());
		model.addAttribute("page", request);
		return "request/listRequest";
	}

}
