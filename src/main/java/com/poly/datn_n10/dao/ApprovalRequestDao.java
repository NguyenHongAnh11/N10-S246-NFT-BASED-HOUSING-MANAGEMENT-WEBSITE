package com.poly.datn_n10.dao;

import com.poly.datn_n10.enity.ApprovalRequest;
import com.poly.datn_n10.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalRequestDao extends JpaRepository<ApprovalRequest, Integer> {
    // Custom query methods (if needed) can be defined here
    List<ApprovalRequest> findByOwner(User owner);

    List<ApprovalRequest> findByStatus(String status);
}

