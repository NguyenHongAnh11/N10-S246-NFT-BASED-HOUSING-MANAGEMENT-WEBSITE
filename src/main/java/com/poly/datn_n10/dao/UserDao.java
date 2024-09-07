package com.poly.datn_n10.dao;

import com.poly.datn_n10.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findById(Integer id);
    User findByEmail(String email);
}
