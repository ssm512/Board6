package com.green.users.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.green.users.dto.UserDTO;

@Mapper
public interface UserMapper {

	List<UserDTO> getUserList();

	void insertUser(UserDTO userDTO);

	void deleteUser(UserDTO userDTO);

	void updateUser(UserDTO userDTO, String oldpwd);

	UserDTO getUser(UserDTO userDTO);

	UserDTO getIdDupCheck(UserDTO userDTO);

	void updateUser2(Map<String, Object> map);
	
}
