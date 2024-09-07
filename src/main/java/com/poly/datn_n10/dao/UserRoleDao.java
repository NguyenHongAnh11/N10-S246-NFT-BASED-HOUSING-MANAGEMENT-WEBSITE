package com.poly.datn_n10.dao;

import com.poly.datn_n10.enity.Role;
import com.poly.datn_n10.enity.User;
import com.poly.datn_n10.enity.UserRole;
import com.poly.datn_n10.enity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, UserRoleId> {
    // Custom query methods (if needed) can be defined here
    List<UserRole> findByUser(User user);

    List<UserRole> findByRole(Role role);
}
