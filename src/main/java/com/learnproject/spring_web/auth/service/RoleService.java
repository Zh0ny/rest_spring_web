package com.learnproject.spring_web.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnproject.spring_web.auth.entity.Role;
import com.learnproject.spring_web.auth.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;

	public Role save(Role Role) {
		return repository.save(Role);
	}

	public Role findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public List<Role> findAll() {
		return repository.findAll();
	}
}
