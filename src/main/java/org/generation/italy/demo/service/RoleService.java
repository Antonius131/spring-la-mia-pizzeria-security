package org.generation.italy.demo.service;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Role;
import org.generation.italy.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepo;
	
public List<Role> findAll() {
		
		return roleRepo.findAll();
	}
	
	public void save(Role role) {
		
		roleRepo.save(role);
	}
	
	public void delete(Role role) {
		
		roleRepo.delete(role);
	}
	
	public Optional<Role> findById(int id) {
		
		return roleRepo.findById(id);
	}
}
