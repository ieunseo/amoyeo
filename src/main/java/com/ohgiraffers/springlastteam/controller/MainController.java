package com.ohgiraffers.springlastteam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(value={"/info"})
    public String info() {return "info/info";}

    @GetMapping(value={"/prices"})
    public String price() {return "price";}

}