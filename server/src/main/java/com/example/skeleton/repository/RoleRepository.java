package com.example.skeleton.repository;

import java.util.Optional;

import com.example.skeleton.model.Role;
import com.example.skeleton.model.RoleName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
