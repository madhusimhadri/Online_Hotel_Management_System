package com.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.model.Employee;
import com.hotel.security.AuthenticationRequest;
import com.hotel.security.AuthenticationResponse;
import com.hotel.security.JwtUtil;
import com.hotel.security.UserDetailsServiceImpl;
import com.hotel.service.ManagerService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthenticationController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	ManagerService managerService;
	
	@Autowired
	JwtUtil jwtTokenUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authRequest) throws Exception{
		try {
			authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		}
		catch(BadCredentialsException e) {
			AuthenticationResponse authResponse = new AuthenticationResponse();
			authResponse.setMessage("Invalid Credentials!");
			authResponse.setStatus(false);
			return ResponseEntity.ok(authResponse);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		Employee loggedInUser = managerService.getEmployeeById(authRequest.getUsername());
		AuthenticationResponse authResponse = new AuthenticationResponse();
		authResponse.setEmployee(loggedInUser);
		authResponse.setMessage("Success!");
		authResponse.setStatus(true);
		authResponse.setJwt(jwt);
		
		return ResponseEntity.ok(authResponse);
	}

	@GetMapping("/403")
	public String accessDenied() {
		return "Access Denied!";
	}
}
