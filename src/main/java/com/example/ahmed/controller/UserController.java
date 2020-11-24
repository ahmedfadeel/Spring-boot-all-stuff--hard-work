package com.example.ahmed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ahmed.model.User;
import com.example.ahmed.model.UserRepository;

@RestController
@CrossOrigin 
@RequestMapping("/users")
public class UserController {

@Autowired
UserRepository userRepository;
@Autowired
PasswordEncoder passEncoder;

@PostMapping("/register")	
public  User saveUser(@RequestBody User userReq ) {
	
	User user=new User();
	
	user.setUserName(userReq.getUserName());
	user.setPassword(passEncoder.encode(userReq.getPassword()) );
	user.setRoles(userReq.getRoles());
	user.setActive(true);
    return  userRepository.save(user);
}
@PreAuthorize("hasRole('admin')")
@GetMapping("")
public List<User> getAllUsers(){
	return  userRepository.findAll();
}
}
