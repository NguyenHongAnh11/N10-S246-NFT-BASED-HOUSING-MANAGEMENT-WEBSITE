package com.poly.datn_n10.controller;

import com.poly.datn_n10.entity.Property;
import com.poly.datn_n10.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/layout/home")
    public String list(Model model) {
        List<Property> list = propertyService.findAll();
        model.addAttribute("properties", list);
        return "layout/home";
    }

    @GetMapping("/propertyDetail/{propertyId}")
    public String detail(Model model, @PathVariable("propertyId") Integer propertyId) {
        Property property = propertyService.findById(propertyId);
        if (property == null) {
            model.addAttribute("error", "Property not found");
            return "error";
        }
        model.addAttribute("property", property);
        return "properties/propertyDetail";
    }

    @GetMapping("/layout/contact")
    public String contact() {
        return "layout/contact";
    }

    @GetMapping("/layout/product")
    public String product(Model model) {
        List<Property> properties = propertyService.findAll(); // Lấy tất cả các property
        model.addAttribute("properties", properties); // Đưa dữ liệu vào model
        return "layout/product"; // Trả về trang product
    }

    @GetMapping("/layout/favorite")
    public String favorite() {
        return "/layout/favorite";
    }

    @PostMapping("/properties/create")
    public String createProperty(@Validated @ModelAttribute("property") Property property, BindingResult result) {
        if (result.hasErrors()) {
            return "/layout/home"; // Trả về trang hiện tại nếu có lỗi
        }
        propertyService.save(property);
        return "redirect:/layout/home";
    }

    //    Phần này của propertyAdmin
    @GetMapping("/Adminproperty")
    public String listProperties(Model model) {
        List<Property> properties = propertyService.findAll();
        model.addAttribute("properties", properties);
        model.addAttribute("property", new Property()); // For form binding
        return "/admin/dashboardProperty";
    }

    @PostMapping("/Adminproperty/create")
    public String createProperty(@ModelAttribute Property property, @RequestParam("image") MultipartFile imageFile) {
        if (!imageFile.isEmpty()) {
            try {
                // Đường dẫn tuyệt đối đến thư mục static/images
                String uploadDir = new ClassPathResource("static/images/").getFile().getAbsolutePath();

                // Tạo đường dẫn đầy đủ cho file
                Path filePath = Paths.get(uploadDir, imageFile.getOriginalFilename());

                // Lưu file vào thư mục static/images
                Files.write(filePath, imageFile.getBytes());

                // Lưu tên file vào cơ sở dữ liệu
                property.setImages(imageFile.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        propertyService.save(property);
        return "redirect:/Adminproperty";
    }


    @GetMapping("/Adminproperty/edit/{id}")
    public String editProperty(@PathVariable Integer id, Model model) {
        Property property = propertyService.findById(id);
        if (property != null) {
            model.addAttribute("property", property);
        } else {
            // Handle the case where the property is not found
            model.addAttribute("property", new Property());
        }
        List<Property> properties = propertyService.findAll();
        model.addAttribute("properties", properties);
        return "/admin/dashboardProperty"; // Ensure this path is correct
    }

    @PostMapping("/Adminproperty/delete/{id}")
    public String deleteProperty(@PathVariable Integer id) {
        propertyService.deleteById(id);
        return "redirect:/Adminproperty"; // Ensure this path is correct
    }

}
