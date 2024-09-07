package com.poly.datn_n10.controller;

import com.poly.datn_n10.enity.Property;
import com.poly.datn_n10.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/Adminuser")
    public String admin() {
        return "admin"; // index.html phải nằm trong /src/main/resources/templates
    }

    @RequestMapping("/")
    public String home() {
        return "/auth/login"; // index.html phải nằm trong /src/main/resources/templates
    }

    @RequestMapping("/index")
    public String index(@AuthenticationPrincipal OAuth2User principal, Model model) {
        // Lấy thông tin từ OAuth2 (người dùng)
        if (principal != null) {
            String name = principal.getAttribute("name");
            String picture = principal.getAttribute("picture"); // Lấy URL ảnh từ Google
            model.addAttribute("name", name);
            model.addAttribute("picture", picture); // Thêm URL ảnh vào model
        }

        // Lấy danh sách sản phẩm (property)
        List<Property> properties = propertyService.getAllProperties();
        model.addAttribute("properties", properties);

        return "index"; // Trả về trang index để hiển thị thông tin người dùng và danh sách tài sản
    }


}
