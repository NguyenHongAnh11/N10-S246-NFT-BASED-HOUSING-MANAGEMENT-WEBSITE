package com.poly.datn_n10.dao;

import com.poly.datn_n10.entity.PasswordResetRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetRequestDao extends JpaRepository<PasswordResetRequest, Integer> {
    // Custom query methods (if needed) can be defined here
    PasswordResetRequest findByResetToken(String resetToken);
}

