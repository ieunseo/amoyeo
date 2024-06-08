package com.ohgiraffers.springlastteam.admin.controller;

import com.ohgiraffers.springlastteam.admin.service.AdminService;
import com.ohgiraffers.springlastteam.entity.BuyingUser;
import com.ohgiraffers.springlastteam.entity.GroupBuying;
import com.ohgiraffers.springlastteam.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/admin-user")
    public String adminUserPage(Model model) {
        List<Users> users = adminService.findAllUsers();
        model.addAttribute("users", users);
        return "admin/admin-user";
    }

    @GetMapping("/admin/admin-post")
    public String adminPostPage(Model model) {
        List<GroupBuying> groupBuyings = adminService.findAllGroupBuyings();
        model.addAttribute("groupBuyings", groupBuyings);
        return "admin/admin-post";
    }

    @GetMapping("/admin/admin-user/delete")
    public String deleteUser(@RequestParam("id") int userId) {
        adminService.deleteUserById(userId);
        return "redirect:/admin/admin-user";
    }

    @GetMapping("/admin/admin-post/delete")
    public String deleteGroupBuying(@RequestParam("id") int buyingId) {
        adminService.deleteGroupBuyingById(buyingId);
        return "redirect:/admin/admin-post";
    }

    @GetMapping("/admin/admin-list")
    public String adminListPage(@RequestParam("buying_no") int buyingNo, Model model) {
        GroupBuying groupBuying = adminService.findGroupBuyingById(buyingNo);
        List<BuyingUser> buyingUsers = adminService.findBuyingUsersByBuyingNo(groupBuying);
        model.addAttribute("buyingUsers", buyingUsers);
        return "admin/admin-list";
    }
}
