package com.ohgiraffers.springlastteam.gonggu.controller;

import com.ohgiraffers.springlastteam.entity.Likes;
import com.ohgiraffers.springlastteam.entity.Users;
import com.ohgiraffers.springlastteam.gonggu.dto.BuyingUserDTO;
import com.ohgiraffers.springlastteam.gonggu.dto.GroupBuyingDTO;
import com.ohgiraffers.springlastteam.gonggu.dto.RequireBuyDTO;
import com.ohgiraffers.springlastteam.gonggu.dto.UserDTO;
import com.ohgiraffers.springlastteam.gonggu.service.DTOService;
import com.ohgiraffers.springlastteam.gonggu.repository.LikesRepository;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class GongguController {
    private final DTOService dtoService;
    private final LikesRepository likesRepository;
    private final ModelMapper modelMapper;

    public GongguController(DTOService dtoService, LikesRepository likesRepository, ModelMapper modelMapper) {
        this.dtoService = dtoService;
        this.likesRepository = likesRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String showDataList(Model model) {
        List<GroupBuyingDTO> groupBuyingList = dtoService.findGroupBuyingList();
        model.addAttribute("groupBuyingList", groupBuyingList);

        return "index";
    }

    @PostMapping("/")
    public String showMain(Model model) {
        List<GroupBuyingDTO> groupBuyingList = dtoService.findGroupBuyingList();
        model.addAttribute("groupBuyingList", groupBuyingList);
        
        return "index";
    }

    @GetMapping("/want")
    public String getRequireBuys(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login"; // 로그인 페이지로 리디렉션
        }
        Users user = (Users) session.getAttribute("user");
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);  // Users 객체를 UserDTO로 변환
        List<RequireBuyDTO> requireBuyList = dtoService.findRequireBuyList(userDTO.getUserNo());
        model.addAttribute("requireBuyList", requireBuyList);
        return "want";
    }

    @PostMapping("/toggleLike")
    public ResponseEntity<Void> toggleLike(@RequestParam int userNo, @RequestParam int requireNo) {
        Optional<Likes> existingLike = likesRepository.findByUserUserNoAndRequireBuyRequireNo(userNo, requireNo);

        if (existingLike.isPresent()) {
            dtoService.removeLike(userNo, requireNo);
        } else {
            dtoService.addLike(userNo, requireNo);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/checkLikeStatus")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> checkLikeStatus(@RequestParam int userNo, @RequestParam int requireNo) {
        boolean liked = likesRepository.findByUserUserNoAndRequireBuyRequireNo(userNo, requireNo).isPresent();
        return ResponseEntity.ok(Collections.singletonMap("liked", liked));
    }

    @GetMapping("/save")
    public String addBuyingUser(BuyingUserDTO newBuyingUser) {
        newBuyingUser.setUserNo(1);
        newBuyingUser.setBuyingNo(3);
        newBuyingUser.setBuyingQuantity(5);
        newBuyingUser.setBuyingDate(new java.util.Date());

        dtoService.requestGroupBuying(newBuyingUser);
        return "data/save";
    }

    @GetMapping("/delete")
    public String deleteBuyingUser(Integer buyingNo, Integer userNo) {
        buyingNo = 3;
        userNo = 1;
        dtoService.deleteRequstBuying(buyingNo, userNo);
        return "data/delete";
    }
}

