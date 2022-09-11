package com.app.smartcontactmanager.service;

import com.app.smartcontactmanager.entity.Contact;
import com.app.smartcontactmanager.entity.User;
import com.app.smartcontactmanager.helper.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ContactService {
    Message SaveContact(User user, Contact contact, MultipartFile file);
    Page<Contact> getAllContacts(User user, Pageable pageable);
    Contact getContactByIdAndUser(String id,User user);

    void deleteContactById(String id,User user);
}
