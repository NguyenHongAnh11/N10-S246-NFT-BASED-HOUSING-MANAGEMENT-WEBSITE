package com.poly.datn_n10.dao;

import com.poly.datn_n10.entity.Rental;
import com.poly.datn_n10.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalDao extends JpaRepository<Rental, Integer> {
    // Custom query methods (if needed) can be defined here
    List<Rental> findByTenant(User tenant);

    List<Rental> findByStatus(String status);
}

