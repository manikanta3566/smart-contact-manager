package com.app.smartcontactmanager.service;

import com.app.smartcontactmanager.entity.Contact;
import com.app.smartcontactmanager.entity.User;
import com.app.smartcontactmanager.helper.Message;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ContactService {
    Message SaveContact(User user, Contact contact, MultipartFile file);
    List<Contact> getAllContacts(User user);
}
