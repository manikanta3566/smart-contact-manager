package com.app.smartcontactmanager.service;

import com.app.smartcontactmanager.entity.User;
import com.app.smartcontactmanager.helper.Message;

import java.security.Principal;

public interface UserService {
    User saveUser(User user);
    User getUserByEmail(Principal principal);

    Message changePassword(User user,String oldPassword,String newPassword);
}
