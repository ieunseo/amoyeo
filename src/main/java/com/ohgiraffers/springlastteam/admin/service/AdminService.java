package com.ohgiraffers.springlastteam.admin.service;

import com.ohgiraffers.springlastteam.admin.repository.AdminRepository;
import com.ohgiraffers.springlastteam.entity.GroupBuying;
import com.ohgiraffers.springlastteam.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository.UserRepository userRepository;

    @Autowired
    private AdminRepository.GroupBuyingRepository groupBuyingRepository;

    @Autowired
    private AdminRepository.ImageRepository imageRepository;

    @Autowired
    private AdminRepository.BuyingUserRepository buyingUserRepository;

    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    public List<GroupBuying> findAllGroupBuyings() {
        return groupBuyingRepository.findAll();
    }

    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }

    public void deleteGroupBuyingById(int buyingId) {
        groupBuyingRepository.deleteById(buyingId);
    }
}
