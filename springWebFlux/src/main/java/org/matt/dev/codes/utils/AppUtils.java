package org.matt.dev.codes.utils;

import org.matt.dev.codes.dto.UserDTO;
import org.matt.dev.codes.entity.User;
import org.springframework.beans.BeanUtils;

public class AppUtils {
	
	public static UserDTO entityToDto(User user) {
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(user, userDTO);
		return userDTO;
	}
	
	public static User dtoToEntity(UserDTO userdto) {
		User user = new User();
		BeanUtils.copyProperties(userdto, user);
		return user;
	}


}
