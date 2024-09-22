package com.poly.datn_n10.dao;

import com.poly.datn_n10.entity.Property;
import com.poly.datn_n10.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyDao extends JpaRepository<Property, Integer> {
    List<Property> findByOwner(User owner);

    List<Property> findByStatus(String status);

    boolean existsByAddressAndType(String address, String type);
}
