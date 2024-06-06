package com.ohgiraffers.springlastteam.gonggu.controller;

import com.ohgiraffers.springlastteam.gonggu.dto.BuyingUserDTO;
import com.ohgiraffers.springlastteam.gonggu.dto.GroupBuyingDTO;
import com.ohgiraffers.springlastteam.gonggu.dto.UserDTO;
import com.ohgiraffers.springlastteam.gonggu.service.DTOService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GongguController {
    private final DTOService dtoService;

    public GongguController(DTOService dtoService) {
        this.dtoService = dtoService;
    }

    @GetMapping("/")
    public String showDataList(Model model) {
        // BuyingUser 출력
        /*List<BuyingUserDTO> buyingList = dtoService.findBuyingUserList();
        model.addAttribute("buyingList", buyingList);
*/
        // GroupBuying 리스트
        List<GroupBuyingDTO> groupBuyingList = dtoService.findGroupBuyingList();
        for(GroupBuyingDTO groupBuyingDTO : groupBuyingList) {
            System.out.println(groupBuyingDTO);
        }
        model.addAttribute("groupBuyingList", groupBuyingList);

        // User 리스트
    /*    List<UserDTO> userList = buyingList.stream()
                .map(buyingUser -> dtoService.findUserById(buyingUser.getUserNo()))
                .collect(Collectors.toList());
        model.addAttribute("userList", userList);
*/
        return "index";
    }


    @GetMapping("save")
    public String addBuyingUser(BuyingUserDTO newBuyingUser) {
        newBuyingUser.setUserNo(1);
        newBuyingUser.setBuyingNo(3);
        newBuyingUser.setBuyingPerson(4);
        newBuyingUser.setBuyingQuantity(5);
        newBuyingUser.setBuyingDate(new java.util.Date());

        dtoService.requestGroupBuying(newBuyingUser);
        return "data/save";
    }

    @GetMapping("delete")
    public String deleteBuyingUser(Integer buyingNo, Integer userNo) {
        buyingNo = 3;
        userNo = 1;
        dtoService.deleteRequstBuying(buyingNo, userNo);
        return "data/delete";
    }
}
