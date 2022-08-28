package com.app.smartcontactmanager.service.impl;

import com.app.smartcontactmanager.entity.User;
import com.app.smartcontactmanager.enums.Role;
import com.app.smartcontactmanager.repository.UserRepository;
import com.app.smartcontactmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        user.setRole(Role.USER.name());
        user.setActive(true);
        user.setImageUrl("default_url");
        return userRepository.save(user);
    }
}
