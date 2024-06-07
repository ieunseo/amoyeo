package com.ohgiraffers.springlastteam.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin-user")
    public String adminUserPage() {
        return "admin/admin-user";
    }

    @GetMapping("/admin-post")
    public String adminPostPage() {
        return "admin/admin-post";
    }
}
