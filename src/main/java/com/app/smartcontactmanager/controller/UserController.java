package com.app.smartcontactmanager.controller;

import com.app.smartcontactmanager.entity.Contact;
import com.app.smartcontactmanager.entity.User;
import com.app.smartcontactmanager.helper.Message;
import com.app.smartcontactmanager.service.ContactService;
import com.app.smartcontactmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ContactService contactService;
    @GetMapping("/dashboard")
    public String userDashboard(Model model, Principal principal){
        User user = userService.getUserByEmail(principal);
        model.addAttribute("user",user);
        model.addAttribute("title","user dashboard");
        return "normal/user_dashboard";
    }

    @GetMapping("/add-contact")
    public String displayContactForm(Model model,Principal principal){
        User user = userService.getUserByEmail(principal);
        model.addAttribute("user",user);
        model.addAttribute("title","add contact");
        return "normal/add_contact_form";
    }

    @PostMapping("/save-contact")
    public String saveContact(@ModelAttribute Contact contact, Model model, @RequestParam("image") MultipartFile file, HttpSession session, Principal principal){
        User user = userService.getUserByEmail(principal);
        Message message= contactService.SaveContact(user, contact,file);
        session.setAttribute("message",message);
        model.addAttribute("user",user);
        return "normal/add_contact_form";
    }

    @GetMapping("/view-contacts")
    public String getAllContacts(Model model,Principal principal){
        User user = userService.getUserByEmail(principal);
        List<Contact> allContacts = contactService.getAllContacts(user);
        model.addAttribute("user",user);
        model.addAttribute("title","view contacts");
        model.addAttribute("contacts",allContacts);
        return "normal/view_contacts";
    }
}
