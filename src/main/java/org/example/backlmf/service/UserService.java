package org.example.backlmf.service;

import org.example.backlmf.entity.User;
import org.example.backlmf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import org.example.backlmf.exception.UsernameAlreadyExistsException;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public boolean isUsernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public User registerUser(User user) throws UsernameAlreadyExistsException {
        if (isUsernameExists(user.getUsername())) {
            throw new UsernameAlreadyExistsException("用户名已存在");
        }
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}