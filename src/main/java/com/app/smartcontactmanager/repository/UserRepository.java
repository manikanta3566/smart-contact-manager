package com.app.smartcontactmanager.repository;

import com.app.smartcontactmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
