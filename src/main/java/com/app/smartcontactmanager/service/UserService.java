package com.app.smartcontactmanager.service;

import com.app.smartcontactmanager.entity.User;

import java.security.Principal;

public interface UserService {
    User saveUser(User user);
    User getUserByEmail(Principal principal);
}
