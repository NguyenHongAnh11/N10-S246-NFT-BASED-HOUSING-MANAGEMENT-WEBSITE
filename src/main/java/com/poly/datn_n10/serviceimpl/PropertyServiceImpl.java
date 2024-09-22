package com.poly.datn_n10.serviceimpl;

import com.poly.datn_n10.dao.PropertyDao;
import com.poly.datn_n10.entity.Property;
import com.poly.datn_n10.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyDao propertyDao;

    @Override
    public List<Property> findAll() {
        return propertyDao.findAll();
    }

    @Override
    public Property findById(Integer id) {
        Optional<Property> property = propertyDao.findById(id);
        return property.orElse(null);  // Trả về null nếu không tìm thấy
    }


    @Override
    @Transactional
    public void create(Property property) {
        if (propertyDao.existsByAddressAndType(property.getAddress(), property.getType())) {
            throw new RuntimeException("Tài sản có cùng địa chỉ và loại đã tồn tại.");
        }
        propertyDao.save(property);
    }

    @Override
    public Property save(Property property) {
        return propertyDao.save(property);
    }

    @Override
    public void deleteById(Integer id) {
        if (propertyDao.existsById(id)) {
            propertyDao.deleteById(id);
        } else {
            throw new RuntimeException("Tài sản không tồn tại.");
        }
    }
}
