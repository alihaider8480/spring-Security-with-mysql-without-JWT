package com.example.practice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.practice.dto.User;
import com.example.practice.repo.UserRepo;

@Service
public class CustUserDetailsService  implements UserDetailsService {
											// userdetailsservice sa hum authentication kar rahe hai isma
											// iski jaga hum jwt bhi kar sakta tha ya auth2 bhi
	@Autowired                              // security ka jo filter ha wo khud isko find karnga
	UserRepo repo;
						// ya loadUserByUsername UserDetailsService iska call ka method override kia hai
	@Override			// 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByEmail(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("User Not Found");
		}
		return user;
	}

}
