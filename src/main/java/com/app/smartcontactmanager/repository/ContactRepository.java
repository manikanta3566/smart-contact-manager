package com.app.smartcontactmanager.repository;

import com.app.smartcontactmanager.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,String> {
}
