package com.example.ahmed.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ahmed.model.MyUserDetails;
import com.example.ahmed.model.User;
import com.example.ahmed.model.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
	@Override
	public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user =userRepository.findByuserName(username);
	    user.orElseThrow(() -> new UsernameNotFoundException ("this user  "+ username + "notFound"));		
	    return user.map(MyUserDetails::new).get();
	}
	 public User save (User user) {
    	 return userRepository.save(user);
    }

}
