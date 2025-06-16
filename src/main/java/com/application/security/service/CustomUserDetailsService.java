package com.application.security.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.application.security.entity.UserInfo;
import com.application.security.repository.UserInfoRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UserInfoRepository userInfoRepo;
	
	public CustomUserDetailsService(UserInfoRepository userInfoRepo) {
		this.userInfoRepo =userInfoRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> user= userInfoRepo.findByUsername(username);
		if(user.isEmpty()) {
			throw new UsernameNotFoundException("username is not exist in database");
		}
		
		return new CustomUserDetails(user.get());
	}

}
