package com.poly.datn_n10.controller;

import com.poly.datn_n10.dao.UserDao;
import com.poly.datn_n10.enity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*") // Cho phép truy cập từ mọi nguồn
@RestController
@RequestMapping("/rest/user")
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping
    public List<User> getAllUsers() {
        return userDao.findAll(); // Lấy tất cả người dùng
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable("id") Integer id) {
        Optional<User> user = userDao.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> post(@RequestBody User user) {
        User savedUser = userDao.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> put(@PathVariable("id") Integer userId, @RequestBody User user) {
        if (userDao.existsById(userId)) {
            user.setUserId(userId);
            User updatedUser = userDao.save(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer userId) {
        if (userDao.existsById(userId)) {
            userDao.deleteById(userId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
