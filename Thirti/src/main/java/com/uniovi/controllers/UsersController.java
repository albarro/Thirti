package com.uniovi.controllers;

import java.security.Principal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Friend;
import com.uniovi.entities.User;
import com.uniovi.services.FriendRequestService;
import com.uniovi.services.FriendService;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {

	@Autowired
	private RolesService rolesService;

	@Autowired 
	private UsersService usersService;
	
	@Autowired
	private FriendService friendService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private SignUpFormValidator signUpFormValidator;

	
	
	@RequestMapping("/user/list" )
	public String getListado(Model model, Pageable pageable,
			@RequestParam(value = "", required=false) String searchtext){
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		if (searchtext != null && !searchtext.isEmpty()) {
			users =  usersService
				.searchUserByEmailAndName(pageable, searchtext);
			
		} else {
		users = usersService.getUsers(pageable);
		}
		model.addAttribute("usersList", users.getContent());
		model.addAttribute("page", users);
		return "user/list";
	}
	
	@RequestMapping("/user/friends" )
	public String getFriends(Model model, Pageable pageable, Principal principal){
		String email = principal.getName(); // Email es el name de la autenticación
		User user = usersService.getUserByEmail(email);
		
		Page<Friend> friends = new PageImpl<Friend>(new LinkedList<Friend>());
		friends = friendService.getFriendsForUser(pageable, user);
		model.addAttribute("friendsList", friends.getContent());
		model.addAttribute("page", friends);
		return "user/friends";
	}

	@RequestMapping(value="/user/add")
	public String getUser(Model model){
		model.addAttribute("rolesList", rolesService.getRoles());
		return "user/add";
	}
	
	
	@RequestMapping(value="/user/add", method=RequestMethod.POST )	
	public String setUser(@ModelAttribute User user){
		usersService.addUser(user);
		return "redirect:/user/list";
	}
	
	@RequestMapping("/user/details/{id}" )
	public String getDetail(Model model, @PathVariable Long id){
		model.addAttribute("user", usersService.getUser(id));
		return "user/details";
	}
	
	@RequestMapping("/user/delete/{id}" )
	public String delete(@PathVariable Long id){
		usersService.deleteUser(id);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/user/edit/{id}")
	public String getEdit(Model model, @PathVariable Long id){
		User user = usersService.getUser(id);
		model.addAttribute("user", user);
		return "user/edit";
	}
	
	@RequestMapping(value="/user/edit/{id}", method=RequestMethod.POST)
	public String setEdit(Model model, @PathVariable Long id, @ModelAttribute User user){
		user.setId(id);
		usersService.addUser(user);
		return "redirect:/user/details/"+id;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
	    return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute @Validated User user, BindingResult result, Model model) {
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}

		user.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
	    return "login";
	}
	
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
	    return "home";
	}
}
