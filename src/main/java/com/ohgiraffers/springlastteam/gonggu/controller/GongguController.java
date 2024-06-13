package com.ohgiraffers.springlastteam.gonggu.controller;

import com.ohgiraffers.springlastteam.entity.GroupBuying;
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
import org.springframework.web.bind.annotation.*;

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
        for(GroupBuyingDTO groupBuyingDTO : groupBuyingList) {
            if(groupBuyingDTO.getBuyingNo() == 10){
                System.out.println("groupBuyinDTO : "+groupBuyingDTO);
            }
        }
        model.addAttribute("groupBuyingList", groupBuyingList);
        return "index";
    }
    @GetMapping("/search")
    public String searchGroupBuying(@RequestParam(value = "query", required = false) String query, Model model) {
        List<GroupBuyingDTO> searchResults;
        if (query == null || query.isEmpty()) {
            searchResults = dtoService.findGroupBuyingList();
        } else {
            searchResults = dtoService.searchGroupBuying(query);
        }
        model.addAttribute("groupBuyingList", searchResults);
        model.addAttribute("query", query);
        if (searchResults.isEmpty()) {
            model.addAttribute("noResultsMessage", "검색결과가 없습니다.");
        }
        return "index";
    }
    @GetMapping("/want")
    public String getRequireBuys(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        Users user = (Users) session.getAttribute("user");
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        List<RequireBuyDTO> requireBuyList = dtoService.findRequireBuyList(userDTO.getUserNo());
        model.addAttribute("requireBuyList", requireBuyList);
        return "want";
    }
    @GetMapping("/want/search")
    public String searchRequireBuys(@RequestParam("query") String query, HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        List<RequireBuyDTO> searchResults;
        if (query == null || query.isEmpty()) {
            Users user = (Users) session.getAttribute("user");
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            searchResults = dtoService.findRequireBuyList(userDTO.getUserNo());
        } else {
            searchResults = dtoService.searchRequireBuys(query);
        }
        model.addAttribute("requireBuyList", searchResults);
        model.addAttribute("query", query);
        if (searchResults.isEmpty()) {
            model.addAttribute("noResultsMessage", "검색결과가 없습니다.");
        }
        return "want";
    }
    @GetMapping("/requireBuy/{requireNo}")
    public String getRequireBuy(@PathVariable int requireNo, Model model) {
        RequireBuyDTO requireBuyDTO = dtoService.findRequireBuyById(requireNo);
        model.addAttribute("requireBuy", requireBuyDTO);
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
    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<GroupBuyingDTO> searchGroupBuying(@RequestParam("query") String query) {
        return dtoService.searchGroupBuying(query);
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
    @PostMapping("/mypage")
    public String requestGroupBuying(@RequestParam int buyingNo,
                                     @RequestParam int quantity,
                                     BuyingUserDTO newBuyingUser,
                                     HttpSession session) {

        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        System.out.println("buyingNo : " + buyingNo);

        newBuyingUser.setBuyingNo(buyingNo);
        newBuyingUser.setUserNo(user.getUserNo());
        newBuyingUser.setBuyingQuantity(quantity);
        newBuyingUser.setBuyingDate(new java.util.Date());

        dtoService.requestGroupBuying(newBuyingUser);

        return "redirect:/?toast=false";
    }

}

