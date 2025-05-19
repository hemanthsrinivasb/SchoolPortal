package com.example.example1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //controlls when to open a page upon which request
public class HomeController {

    @RequestMapping(value={"/home","/",""})
    public String displayHomePage(){
        return "home.html";
    }
}
