package com.shikhar.blog.services.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.shikhar.blog.entities.User;
import com.shikhar.blog.exceptions.ResourceNotFoundException;
import com.shikhar.blog.payloads.UserDto;
import com.shikhar.blog.repositories.UserRepo;
import com.shikhar.blog.services.UserService;

import net.bytebuddy.asm.Advice.This;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userdto) {
		User user = this.dtotoUser(userdto);
		User savedUser = this.userRepo.save(user);
		return this.usertodto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setAbout(userdto.getAbout());
		user.setPassword(userdto.getPassword());
        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 = this.usertodto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id", userId));
		return this.usertodto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.usertodto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id", userId));
		this.userRepo.delete(user);
	}
	
	private User dtotoUser(UserDto userdto) {
		User user = this.modelMapper.map(userdto, User.class);
		return user;
	}
	
	private UserDto usertodto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
