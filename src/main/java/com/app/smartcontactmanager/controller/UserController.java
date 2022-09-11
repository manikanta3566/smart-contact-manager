package com.app.smartcontactmanager.controller;

import com.app.smartcontactmanager.entity.Contact;
import com.app.smartcontactmanager.entity.User;
import com.app.smartcontactmanager.helper.Message;
import com.app.smartcontactmanager.service.ContactService;
import com.app.smartcontactmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.security.Principal;

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

    @GetMapping("/view-contacts/{page}")
    public String getAllContacts( @PathVariable("page")int page, Model model,Principal principal){
        User user = userService.getUserByEmail(principal);
        Pageable pageable = PageRequest.of(page, 5);
        Page<Contact> allContacts = contactService.getAllContacts(user,pageable);
        model.addAttribute("user",user);
        model.addAttribute("title","view contacts");
        model.addAttribute("contacts",allContacts.getContent());
        model.addAttribute("pageNumber",page);
        model.addAttribute("totalPages",allContacts.getTotalPages());
        return "normal/view_contacts";
    }

    @GetMapping("/contacts/{id}")
    public String showSingleContact(@PathVariable("id") String id, Model model, Principal principal) {
        try {
            User user = userService.getUserByEmail(principal);
            Contact contact = contactService.getContactByIdAndUser(id, user);
            model.addAttribute("user",user);
            model.addAttribute("contact",contact);
            model.addAttribute("title","view-single-contact");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "normal/view_single_contact";
    }

    @GetMapping("/contacts/delete/{id}")
    public String deleteContact(@PathVariable("id") String id, Model model, Principal principal,HttpSession session){
        try {
            User user = userService.getUserByEmail(principal);
            contactService.deleteContactById(id,user);
            model.addAttribute("user",user);
            session.setAttribute("message",new Message("contact is deleted successfully","success"));
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message",new Message("something went wrong!!","danger"));
        }
        return "redirect:/user/view-contacts/0";
    }
}
