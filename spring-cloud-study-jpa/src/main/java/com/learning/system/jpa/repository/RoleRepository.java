package com.learning.system.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.system.jpa.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
}
