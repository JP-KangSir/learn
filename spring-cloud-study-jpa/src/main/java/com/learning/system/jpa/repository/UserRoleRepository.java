package com.learning.system.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.system.jpa.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
}
