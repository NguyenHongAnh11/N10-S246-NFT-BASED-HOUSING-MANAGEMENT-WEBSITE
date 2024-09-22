package com.poly.datn_n10.controller;

import com.poly.datn_n10.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/Adminuser")
    public String admin() {
        return "/admin/dashboardUser";
    }



}