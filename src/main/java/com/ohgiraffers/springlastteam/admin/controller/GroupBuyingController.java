package com.ohgiraffers.springlastteam.admin.controller;

import com.ohgiraffers.springlastteam.admin.service.GroupBuyingService;
import com.ohgiraffers.springlastteam.entity.GroupBuying;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/groupbuyings")
public class GroupBuyingController {
    @Autowired
    private GroupBuyingService groupBuyingService;

    @GetMapping
    public String listGroupBuyings(Model model) {
        List<GroupBuying> groupBuyings = groupBuyingService.getAllGroupBuyings();
        model.addAttribute("groupBuyings", groupBuyings);
        return "/admin";
    }

    @GetMapping("/{id}")
    public String getGroupBuyingById(@PathVariable("id") int id, Model model) {
        GroupBuying groupBuying = groupBuyingService.getGroupBuyingById(id);
        model.addAttribute("groupBuying", groupBuying);
        return "/admin";
    }

    @PostMapping
    public String saveGroupBuying(@ModelAttribute GroupBuying groupBuying) {
        groupBuyingService.saveGroupBuying(groupBuying);
        return "/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteGroupBuying(@PathVariable("id") int id) {
        groupBuyingService.deleteGroupBuying(id);
        return "redirect:/";
    }
}
