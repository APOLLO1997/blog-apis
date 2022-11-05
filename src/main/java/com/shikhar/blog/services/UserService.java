package com.shikhar.blog.services;

import java.util.List;

import com.shikhar.blog.payloads.UserDto;


public interface UserService {
 
	UserDto createUser(UserDto user);
	UserDto getUserById(Integer usedId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	UserDto updateUser(UserDto user, Integer userId);
	
}
