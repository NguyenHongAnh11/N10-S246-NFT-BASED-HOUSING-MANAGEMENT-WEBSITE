package com.poly.datn_n10.dao;

import com.poly.datn_n10.entity.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyTypeDao extends JpaRepository<PropertyType, Integer> {
    // Custom query methods (if needed) can be defined here
    PropertyType findByTypeName(String typeName);
}

