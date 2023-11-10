package com.taskmanager.taskmanager.config;

import com.taskmanager.taskmanager.domain.User;
import com.taskmanager.taskmanager.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByName(name);
        return user.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found " + name));

    }}
