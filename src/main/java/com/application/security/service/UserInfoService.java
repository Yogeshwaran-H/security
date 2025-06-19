package com.application.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.security.entity.UserInfo;
import com.application.security.model.UserInfoDTO;
import com.application.security.repository.UserInfoRepository;

@Service
public class UserInfoService {

	@Autowired UserInfoRepository userInfoRepo;

	@Autowired PasswordEncoder encoder;

	public String addUser(UserInfoDTO userInfoDTO) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(userInfoDTO.getUserName());
		userInfo.setPassword(encoder.encode(userInfoDTO.getPassWord()));
		userInfoRepo.save(userInfo);
		return "Successfully Updated";
	}

}
