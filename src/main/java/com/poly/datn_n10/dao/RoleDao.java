package com.poly.datn_n10.dao;

import com.poly.datn_n10.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
    // Custom query methods (if needed) can be defined here
    Role findByRoleName(String roleName);
}

