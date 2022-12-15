package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.User;
import org.generation.italy.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	
	public List<User> findAll() {
		
		return userRepo.findAll();
	}
	
	public void save(User user) {
		
		userRepo.save(user);
	}
	
	public void delete(User user) {
		
		userRepo.delete(user);
	}
	
	public Optional<User> findById(int id) {
		
		return userRepo.findById(id);
	}
}