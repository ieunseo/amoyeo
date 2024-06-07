package com.ohgiraffers.springlastteam.admin.controller;

import com.ohgiraffers.springlastteam.admin.service.UserService;
import com.ohgiraffers.springlastteam.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        List<Users> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        Users user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user/detail";
    }

    @PostMapping
    public String saveUser(@ModelAttribute Users user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
