package com.app.smartcontactmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title","Home-smart-contact-manager");
        return "Home";
    }

    @GetMapping("/signup")
    public String signUp(Model model){
        model.addAttribute("title","Signup-smart-contact-manager");
        return "Signup";
    }



}
