package com.poly.datn_n10.dao;

import com.poly.datn_n10.enity.Property;
import com.poly.datn_n10.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyDao extends JpaRepository<Property, Integer> {
    // Custom query methods (if needed) can be defined here
    List<Property> findByOwner(User owner);

    List<Property> findByStatus(String status);

    boolean existsByAddressAndType(String address, String type);
}
