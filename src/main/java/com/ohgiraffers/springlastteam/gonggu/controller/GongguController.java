package com.ohgiraffers.springlastteam.gonggu.controller;

import com.ohgiraffers.springlastteam.gonggu.dto.BuyingUserDTO;
import com.ohgiraffers.springlastteam.gonggu.dto.GroupBuyingDTO;
import com.ohgiraffers.springlastteam.gonggu.dto.UserDTO;
import com.ohgiraffers.springlastteam.gonggu.service.DTOService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
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

    @GetMapping("save")
    public String addBuyingUser(BuyingUserDTO newBuyingUser) {

        newBuyingUser.setUserNo(1);
        newBuyingUser.setBuyingNo(3);
        newBuyingUser.setBuyingPerson(4);
        newBuyingUser.setBuyingQuantity(5);
        newBuyingUser.setBuyingDate(new Date());

        System.out.println("컨트롤러 클래스 서비스로 가기 전");
        dtoService.requestGroupBuying(newBuyingUser);
        System.out.println("서비스 갔다온 후");
        return "data/save";
    }

    @GetMapping("delete")
    public String deleteBuyingUser(Integer buyingNo, Integer userNo){
        buyingNo = 3;
        userNo = 1;
        System.out.println("서비스 가기전 컨트롤러");
        dtoService.deleteRequstBuying(buyingNo,userNo);
        System.out.println("서비스 갔다온 후 컨트롤러");
        return"data/delete";
    }
}
