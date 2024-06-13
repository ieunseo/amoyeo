package com.ohgiraffers.springlastteam.admin.controller;

import com.ohgiraffers.springlastteam.admin.dto.ImageDTO;
import com.ohgiraffers.springlastteam.admin.service.AdminService;
import com.ohgiraffers.springlastteam.entity.BuyingUser;
import com.ohgiraffers.springlastteam.entity.GroupBuying;
import com.ohgiraffers.springlastteam.entity.Image;
import com.ohgiraffers.springlastteam.entity.Users;
import com.ohgiraffers.springlastteam.gonggu.dto.BuyingUserDTO;
import com.ohgiraffers.springlastteam.gonggu.dto.GroupBuyingDTO;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    

    @Autowired
    ResourceLoader resourceLoader;


    /*private String imgPath;

    public AdminController(@Value("${app.upload-path}") String imgPath){
        this.imgPath = imgPath;
    }*/

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
        System.out.println("buyingNO : " + buyingNo);
        GroupBuying groupBuying = adminService.findGroupBuyingById(buyingNo);
        System.out.println("text : " + groupBuying.getBuyingText());
        List<BuyingUser> buyingUsers = adminService.findBuyingUsersByBuyingNo(groupBuying);
        for(BuyingUser buyingUser : buyingUsers) {
            System.out.println("buyingUser : " + buyingUser);
        }
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
    public String postAddPostPage(@RequestParam(value = "image", required = false)List<MultipartFile> multiFiles,
                                  @RequestParam("buying_text")String buyingText,
                                  @RequestParam("buying_item")String buyingItem,
                                  @RequestParam("buying_price")int buyingPrice,
                                  @RequestParam("buying_quality")String buyingQuality,
                                  GroupBuyingDTO newGroupBuying,
                                  Model model,
                                  HttpSession session) throws IOException {

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

        GroupBuying save = adminService.registGroupBuying(newGroupBuying);

        /* 파일 경로 설정*/
        Resource resource = resourceLoader.getResource("classpath:static/img");
        String filePath = null;

        if(!resource.exists()){
            String root = "src/main/resources/static/img";
            File file = new File(root);
            file.mkdir();

            filePath = file.getAbsolutePath();
        } else {
            filePath = resource.getFile().getAbsolutePath();
        }

        /* 파일 정보 저장*/
        List<ImageDTO> images = new ArrayList<>();


        try{
            for(MultipartFile file : multiFiles){
                /* 파일명 변경 처리*/
                String originFilename = file.getOriginalFilename();
                String ext = originFilename.substring(originFilename.lastIndexOf("."));
                String imgName = UUID.randomUUID().toString().replace("-", "") + ext;


                ImageDTO imageDTO = new ImageDTO(originFilename,imgName,filePath);
                imageDTO.setGroupBuyingNo(save.getBuyingNo());
                images.add(imageDTO);

                file.transferTo(new File(filePath + "/" + imgName));

            }
        } catch (Exception e){
            e.printStackTrace();
        }

        for(ImageDTO imageDTO : images){
            System.out.println(imageDTO);
        }
        System.out.println(newGroupBuying);

        adminService.registImages(images);

        return "redirect:/";
    }
}
