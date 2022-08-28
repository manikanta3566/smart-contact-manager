package com.app.smartcontactmanager.controller;

import com.app.smartcontactmanager.entity.User;
import com.app.smartcontactmanager.helper.Message;
import com.app.smartcontactmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title","Home-smart-contact-manager");
        return "Home";
    }

    @GetMapping("/signup")
    public String signUp(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("title","Signup-smart-contact-manager");
        return "Signup";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session) {
        try {
            if(bindingResult.hasErrors()){
                model.addAttribute("user",user);
                return "Signup";
            }
            if (!agreement) {
                throw new Exception("something went wrong !! you have not agreed terms and conditions");
            }
            User savedUser = userService.saveUser(user);
            model.addAttribute("user", new User());
            session.setAttribute("message", new Message("you have successfully registered", "alert-success"));
            return "Signup";
        } catch (Exception e) {
            model.addAttribute("user", user);
            session.setAttribute("message", new Message(e.getMessage(), "alert-danger"));
            return "Signup";
        }

    }



}
