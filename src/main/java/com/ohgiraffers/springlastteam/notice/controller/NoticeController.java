package com.ohgiraffers.springlastteam.notice.controller;

import com.ohgiraffers.springlastteam.entity.Notice;
import com.ohgiraffers.springlastteam.entity.Users;
import com.ohgiraffers.springlastteam.notice.repository.UserRepository;
import com.ohgiraffers.springlastteam.notice.service.NoticeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/info")
public class NoticeController {

    private final NoticeService noticeService;
    private final UserRepository userRepository;

    @Autowired
    public NoticeController(NoticeService noticeService, @Qualifier("NoticeUserRepository") UserRepository userRepository) {
        this.noticeService = noticeService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getAllNotices(Model model, HttpSession session) {
        List<Notice> notices = noticeService.findAllNotices();
        model.addAttribute("notices", notices);
        Users user = (Users) session.getAttribute("user");
        if (user != null && "Y".equals(user.getUserRights())) {
            session.setAttribute("isAdmin", true);
        } else {
            session.setAttribute("isAdmin", false);
        }
        return "info/info";
    }

    @GetMapping("/{id}")
    public String getNoticeById(@PathVariable("id") int id, Model model) {
        Notice notice = noticeService.findNoticeById(id);
        model.addAttribute("notice", notice);
        return "info/notice_detail";
    }

    @GetMapping("/create")
    public String createNoticeForm(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user != null && "Y".equals(user.getUserRights())) {
            return "info/notice_form";
        }
        return "redirect:/notice";
    }

    @PostMapping("/create")
    public String createNotice(@RequestParam String title, @RequestParam String content, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/notice";
        }
        noticeService.saveNotice(title, content, user.getUserId());
        return "redirect:/notice";
    }
}
