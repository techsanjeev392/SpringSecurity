package com.sanjeev.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjeev.learning.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
