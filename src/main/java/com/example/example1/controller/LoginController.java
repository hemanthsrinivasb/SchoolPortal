package com.example.example1.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Slf4j
@Controller
public class LoginController {
    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public String displayLoginPage(@RequestParam(value = "error",required = false) String error,
                                   @RequestParam(value="logout",required = false) String logout, Model model){
        String errorMessge=null;
        if(error!=null)errorMessge="username or password is incorrect";
        if(logout!=null)errorMessge="you have been succesfully logged out";
        model.addAttribute("errorMessge",errorMessge);
        return "login.html";
    }

//    as the csrf blocks the default logout functionality written in securityconfig
    @RequestMapping(value = "/logout",method = {RequestMethod.GET})
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return "redirect:/login?logout=true";
    }
}
