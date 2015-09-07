package com.overlord.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.overlord.model.User;
import com.overlord.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	public User findByLogin(String userName, String password) {	
		User user = userRepository.findByUserName(userName);
		
		if(user != null && user.getPassword().equals(password)) {
			return user;
		} 
		
		return new User();		
	}

	public boolean findByUserName(String userName) {
		User user = userRepository.findByUserName(userName);
		if(user != null) {
			return true;
		} 
		return false;	
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Transactional
	public void deleteUser(String userId) {
		userRepository.delete(Integer.parseInt(userId));
	}
	

	public User findByUserId(String userId) {
		return userRepository.findOne(Integer.parseInt(userId));
	}
	
	@Transactional
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	@Transactional
	public User updateUser(User user) {
		return userRepository.save(user);
	}

}
