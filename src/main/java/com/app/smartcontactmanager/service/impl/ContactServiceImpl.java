package com.app.smartcontactmanager.service.impl;

import com.app.smartcontactmanager.entity.Contact;
import com.app.smartcontactmanager.entity.User;
import com.app.smartcontactmanager.helper.Message;
import com.app.smartcontactmanager.repository.ContactRepository;
import com.app.smartcontactmanager.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Message SaveContact(User user, Contact contact, MultipartFile file) {
        try {
            contact.setUser(user);
            contact.setImageName(file.getOriginalFilename());
            File destFile= new ClassPathResource("/static/img").getFile();
            Path path = Paths.get(destFile.getPath() + File.separator + file.getOriginalFilename());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            contactRepository.save(contact);
        } catch (Exception e) {
            log.error("error while saving contact {}", e);
//            return new Message(e.getMessage(),"danger");
            return new Message("something went wrong", "danger");
        }
        return new Message("contact added successfully", "success");
    }

    @Override
    public List<Contact> getAllContacts(User user) {
        List<Contact> contactList = contactRepository.findByUser(user);
        return contactList;
    }
}
