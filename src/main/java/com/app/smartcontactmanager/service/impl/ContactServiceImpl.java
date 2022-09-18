package com.app.smartcontactmanager.service.impl;

import com.app.smartcontactmanager.entity.Contact;
import com.app.smartcontactmanager.entity.User;
import com.app.smartcontactmanager.helper.Message;
import com.app.smartcontactmanager.repository.ContactRepository;
import com.app.smartcontactmanager.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Message SaveContact(User user, Contact contact, MultipartFile file) {
        try {
            contact.setUser(user);
            if(!file.isEmpty()) {
                contact.setImageName(file.getOriginalFilename());
                File destFile = new ClassPathResource("/static/img").getFile();
                Path path = Paths.get(destFile.getPath() + File.separator + file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }else{
                contact.setImageName("contact.png");
            }
            contactRepository.save(contact);
        } catch (Exception e) {
            log.error("error while saving contact {}", e);
//            return new Message(e.getMessage(),"danger");
            return new Message("something went wrong", "danger");
        }
        return new Message("contact added successfully", "success");
    }

    @Override
    public Page<Contact> getAllContacts(User user,Pageable  pageable) {
        Page<Contact> contactList = contactRepository.findByUser(user,pageable);
        return contactList;
    }

    @Override
    public Contact getContactByIdAndUser(String id, User user) {
        Optional<Contact> contact = contactRepository.findByIdAndUser(id, user);
        if(contact.isPresent()){
            return contact.get();
        }else {
            throw new RuntimeException("Contact not found");
        }

    }

    @Override
    public void deleteContactById(String id, User user) {
        Optional<Contact> contact = contactRepository.findByIdAndUser(id, user);
        if(contact.isPresent()){
            contact.get().setUser(null);
             contactRepository.delete(contact.get());
        }else {
            throw new RuntimeException("Contact not found");
        }
    }

    @Override
    public Contact updateContact(Contact contact, User user, MultipartFile file, String id) throws IOException {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if(!contactOptional.isPresent()){
            throw new RuntimeException("contact not found");
        }
        contactOptional.get().setUser(user);
        contactOptional.get().setEmail(contact.getEmail());
        contactOptional.get().setPhone(contact.getPhone());
        contactOptional.get().setDescription(contact.getDescription());
        contactOptional.get().setWork(contact.getWork());
        contactOptional.get().setName(contact.getName());
        if(!file.isEmpty()) {
            contactOptional.get().setImageName(file.getOriginalFilename());
            File destFile = new ClassPathResource("/static/img").getFile();
            Path path = Paths.get(destFile.getPath() + File.separator + file.getOriginalFilename());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        }
        Contact saveContact = contactRepository.save(contactOptional.get());
        return saveContact;
    }

    @Override
    public List<Contact> searchContacts(String name, User user) {
        return contactRepository.findByNameContainingAndUser(name,user);
    }
}
