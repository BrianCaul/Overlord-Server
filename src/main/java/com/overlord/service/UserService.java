package com.overlord.service;

import java.util.List;

import com.overlord.model.User;

public interface UserService {
	User save(User user);
	User findByLogin(String userName, String password);
	boolean findByUserName(String userName);
	List<User> getAllUsers();
	void deleteUser(String userid);
	User createUser(User user);
	User findByUserId(String userid);
	User updateUser(User user);
}
