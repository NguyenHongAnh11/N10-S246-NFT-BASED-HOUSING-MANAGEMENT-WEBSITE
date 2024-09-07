package com.poly.datn_n10.controller;

import com.poly.datn_n10.enity.Property;
import com.poly.datn_n10.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/properties/create")
    public String createProperty(@Validated @ModelAttribute("property") Property property, BindingResult result) {
        if (result.hasErrors()) {
            return "index"; // Trả về trang hiện tại nếu có lỗi
        }
        propertyService.save(property);
        return "redirect:/index";
    }

}
