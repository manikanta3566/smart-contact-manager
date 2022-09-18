package com.app.smartcontactmanager.service;

import com.app.smartcontactmanager.entity.Contact;
import com.app.smartcontactmanager.entity.User;
import com.app.smartcontactmanager.helper.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ContactService {
    Message SaveContact(User user, Contact contact, MultipartFile file);
    Page<Contact> getAllContacts(User user, Pageable pageable);
    Contact getContactByIdAndUser(String id,User user);

    void deleteContactById(String id,User user);

    Contact updateContact(Contact contact,User user,MultipartFile file,String id) throws IOException;

    List<Contact> searchContacts(String name,User user);

}
