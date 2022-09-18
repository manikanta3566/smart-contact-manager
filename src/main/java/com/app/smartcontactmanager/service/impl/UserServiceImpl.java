package com.app.smartcontactmanager.service.impl;

import com.app.smartcontactmanager.entity.User;
import com.app.smartcontactmanager.enums.Role;
import com.app.smartcontactmanager.helper.Message;
import com.app.smartcontactmanager.repository.UserRepository;
import com.app.smartcontactmanager.security.CustomUserDetails;
import com.app.smartcontactmanager.service.UserService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Objects;

@Slf4j
@Service
@NoArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(Set.of(Role.USER.name()));
        user.setRole(Role.USER.name());
        user.setActive(true);
        user.setImageUrl("default.png");
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByEmail(username);
        return user;
    }

    @Override
    public Message changePassword(User user, String oldPassword, String newPassword) {
        if(passwordEncoder.matches(oldPassword,user.getPassword())){
            user.setPassword(passwordEncoder.encode(newPassword));
            log.info("password updated");
            userRepository.save(user);
        }else{
            log.error("old password incorrect");
            return new Message("Please Enter the old password Correctly","danger");
        }
        return new Message("password changed successfully","success");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("user not found with username " + username);
        }
        return new CustomUserDetails(user);
    }
}
