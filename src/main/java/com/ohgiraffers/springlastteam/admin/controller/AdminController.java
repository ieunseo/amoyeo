package com.ohgiraffers.springlastteam.admin.controller;

import com.ohgiraffers.springlastteam.admin.service.AdminService;
import com.ohgiraffers.springlastteam.entity.BuyingUser;
import com.ohgiraffers.springlastteam.entity.GroupBuying;
import com.ohgiraffers.springlastteam.entity.Users;
import com.ohgiraffers.springlastteam.gonggu.dto.BuyingUserDTO;
import com.ohgiraffers.springlastteam.gonggu.dto.GroupBuyingDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin-user")
    public String adminUserPage(Model model) {
        List<Users> users = adminService.findAllUsers();
        model.addAttribute("users", users);
        return "admin/admin-user";
    }

    @GetMapping("/admin-post")
    public String adminPostPage(Model model) {
        List<GroupBuying> groupBuyings = adminService.findAllGroupBuyings();
        model.addAttribute("groupBuyings", groupBuyings);
        return "admin/admin-post";
    }

    @GetMapping("/admin-user/delete")
    public String deleteUser(@RequestParam("id") int userId) {
        adminService.deleteUserById(userId);
        return "redirect:/admin/admin-user";
    }

    @GetMapping("/admin-post/delete")
    public String deleteGroupBuying(@RequestParam("id") int buyingId) {
        adminService.deleteGroupBuyingById(buyingId);
        return "redirect:/admin/admin-post";
    }

    @GetMapping("/admin-list")
    public String adminListPage(@RequestParam("buying_no") int buyingNo, Model model) {
        GroupBuying groupBuying = adminService.findGroupBuyingById(buyingNo);
        List<BuyingUser> buyingUsers = adminService.findBuyingUsersByBuyingNo(groupBuying);
        model.addAttribute("buyingUsers", buyingUsers);
        return "admin/admin-list";
    }

    /*화면 띄우기용*/
    @GetMapping("/addpost")
    public void getAddPostPage(HttpSession session, Model model){
        Users user = (Users) session.getAttribute("user");
        model.addAttribute("user", user);
    }

    /*값 전달*/
    @PostMapping("/addpost")
    public String postAddPostPage(@RequestParam(value = "image", required = false)List<MultipartFile> image,
                                  @RequestParam("buying_text")String buyingText,
                                  @RequestParam("buying_item")String buyingItem,
                                  @RequestParam("buying_price")int buyingPrice,
                                  @RequestParam("buying_quality")String buyingQuality,
                                  GroupBuyingDTO newGroupBuying,
                                  Model model,
                                  HttpSession session){
        System.out.println("세션확인");

        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);

        newGroupBuying.setBuyingText(buyingText);
        newGroupBuying.setBuyingItem(buyingItem);
        newGroupBuying.setBuyingPrice(buyingPrice);
        newGroupBuying.setBuyingQuality(buyingQuality);
        newGroupBuying.setUserNo(user.getUserNo());

        System.out.println("포스트 실행");
        adminService.registGroupBuying(newGroupBuying);
        System.out.println("포스트 서비스 실행 후");
        return "redirect:/";
    }
}
