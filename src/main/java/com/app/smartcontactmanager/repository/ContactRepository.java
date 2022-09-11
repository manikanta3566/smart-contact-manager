package com.app.smartcontactmanager.repository;

import com.app.smartcontactmanager.entity.Contact;
import com.app.smartcontactmanager.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact,String> {
    Page<Contact> findByUser(User user, Pageable pageable);
    Optional<Contact> findByIdAndUser(String id, User user);
}
