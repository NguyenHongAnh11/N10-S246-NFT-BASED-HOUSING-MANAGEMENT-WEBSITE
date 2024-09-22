package com.poly.datn_n10.service;

import com.poly.datn_n10.entity.Property;

import java.util.List;

public interface PropertyService {
    List<Property> findAll();

    Property findById(Integer id);

    void create(Property property);

    Property save(Property property);

    void deleteById(Integer id);
}

