package com.poly.datn_n10.rest.controller;

import com.poly.datn_n10.entity.Property;
import com.poly.datn_n10.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/properties")
public class PropertyRestController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.findAll();
    }

    @GetMapping("/{propertyId}")
    public Property getPropertyById(@PathVariable("propertyId") Integer propertyId) {
        return propertyService.findById(propertyId);
    }
}
