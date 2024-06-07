package com.ohgiraffers.springlastteam.admin.service;

import com.ohgiraffers.springlastteam.admin.repository.UserRepository;
import com.ohgiraffers.springlastteam.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(int userNo) {
        return userRepository.findById(userNo).orElse(null);
    }

    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    public void deleteUser(int userNo) {
        userRepository.deleteById(userNo);
    }
}
