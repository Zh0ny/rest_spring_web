package com.learnproject.spring_web;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.learnproject.spring_web.auth.entity.Role;
import com.learnproject.spring_web.auth.entity.User;
import com.learnproject.spring_web.auth.service.RoleService;
import com.learnproject.spring_web.auth.service.UserService;

import jakarta.annotation.PostConstruct;

@Service
public class Initializer {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@PostConstruct
	public void criaUsuariosEPermissoes() {
		Role roleAdmin = new Role();

		roleAdmin.setName("ROLE_ADMIN");

		roleService.save(roleAdmin);

		User user = new User();
		user.setAtivo(true);
		user.setEmail("your_email");
		user.setNome("your_name");
		user.setSenha(new BCryptPasswordEncoder().encode("your_password"));
		user.setUsername("your_username");
		user.setRoles(Arrays.asList(roleAdmin));

		userService.save(user);

	}
}
