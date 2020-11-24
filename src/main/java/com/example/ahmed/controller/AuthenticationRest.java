package com.example.ahmed.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ahmed.JwtUtil;
import com.example.ahmed.exception.ResourceNotFoundException;
import com.example.ahmed.model.AuthenticationRequest;
import com.example.ahmed.model.AuthenticationResponse;
import com.example.ahmed.model.Employee;
import com.example.ahmed.model.MyUserDetails;
import com.example.ahmed.model.User;
import com.example.ahmed.model.UserRepository;
import com.example.ahmed.service.MyUserDetailsService;

import io.jsonwebtoken.impl.DefaultClaims;

@RestController
@CrossOrigin
public class AuthenticationRest {
	
@Autowired()
AuthenticationManager authenticationManager;

@Autowired
MyUserDetailsService userService;
@Autowired
UserRepository UserRepository;

@Autowired
JwtUtil jwtUtil;

Logger logger = LoggerFactory.getLogger(EmployeeController.class);

@PostMapping("/authenticate")
public  ResponseEntity<?> createAuthenticateToken(@RequestBody AuthenticationRequest authenaticationRequest) throws Exception {
	try{authenticationManager.authenticate(
	  new UsernamePasswordAuthenticationToken(authenaticationRequest.getUserName(), authenaticationRequest.getPassword()));
	}
	catch(BadCredentialsException badCredEx) {
		
		 throw new Exception ("bad userName or password ");
	}
	final MyUserDetails userDetail=userService
			.loadUserByUsername(authenaticationRequest.getUserName());
	
	if (userDetail== null)
		throw new ResourceNotFoundException("this user is not found ");
			
	final String jwt=jwtUtil.generateToken(userDetail);
	
	return ResponseEntity.ok(new AuthenticationResponse(jwt));
}
@GetMapping( "/refreshtoken")
public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
	// From the HttpRequest get the claims
	DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
	
    logger.info("in referesh token " + claims);       
	Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
	logger.info("in referesh token " + expectedMap);   
	String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
	return ResponseEntity.ok(new AuthenticationResponse(token));
}

public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
	Map<String, Object> expectedMap = new HashMap<String, Object>();
	for (Entry <String, Object> entry : claims.entrySet()) {
		expectedMap.put(entry.getKey(), entry.getValue());
	}
	return expectedMap;
}

	
}
