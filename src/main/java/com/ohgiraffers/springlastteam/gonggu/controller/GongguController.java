package com.ohgiraffers.springlastteam.gonggu.controller;

import com.ohgiraffers.springlastteam.gonggu.dto.BuyingUserDTO;
import com.ohgiraffers.springlastteam.gonggu.dto.GroupBuyingDTO;
import com.ohgiraffers.springlastteam.gonggu.dto.UserDTO;
import com.ohgiraffers.springlastteam.gonggu.service.DTOService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GongguController {

    private DTOService dtoService;

    public GongguController(DTOService dtoService) {
        this.dtoService = dtoService;
    }

    @GetMapping("/list")
    public String showDataList(Model model) {
        /* user 출력 */
/*        List<UserDTO> usersList = dtoService.finduserList();

        for(UserDTO userDTO : usersList) {
            System.out.println(userDTO.toString());
        }
        model.addAttribute("usersList", usersList);*/

        /* groupBuying 출력*/
        /*List<GroupBuyingDTO> groupList = dtoService.findGroupBuyingList();

        for(GroupBuyingDTO groupBuyingDTO : groupList) {
            System.out.println(groupBuyingDTO);
        }*/

        /* BuyingUser 출력*/
        List<BuyingUserDTO> buyingList = dtoService.findBuyingUserList();
        for(BuyingUserDTO buyingUserDTO : buyingList) {
            System.out.println(buyingUserDTO);
        }
        return "data/list";
    }
}
