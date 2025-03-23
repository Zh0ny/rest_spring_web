package com.learnproject.spring_web.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnproject.spring_web.auth.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
