package com.poly.datn_n10.service;

import com.poly.datn_n10.dao.PropertyDao;
import com.poly.datn_n10.enity.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyDao propertyDao;

    public void save(Property property) {
        if (propertyDao.existsByAddressAndType(property.getAddress(), property.getType())) {
            throw new RuntimeException("Tài sản có cùng địa chỉ và loại đã tồn tại..");
        }
        propertyDao.save(property);
    }

    public List<Property> getAllProperties() {
        return propertyDao.findAll();  // Trả về tất cả các properties
    }
}
