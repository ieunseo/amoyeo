package com.ohgiraffers.springlastteam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(value = {"/"})
    public String main() {
        return "index";
    }
    @GetMapping(value = {"/mypage"})
    public String mypage() {
        return "mypage";
    }
    @GetMapping(value={"/prices"})
    public String price() {return "price";}

}
