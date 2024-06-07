package com.ohgiraffers.springlastteam.admin.service;

//import com.ohgiraffers.springlastteam.admin.repository.AdminRepository;
import com.ohgiraffers.springlastteam.admin.repository.BuyingUserRepository;
import com.ohgiraffers.springlastteam.admin.repository.GroupBuyingRepository;
import com.ohgiraffers.springlastteam.admin.repository.ImageRepository;
import com.ohgiraffers.springlastteam.admin.repository.UserRepository;
import com.ohgiraffers.springlastteam.entity.GroupBuying;
import com.ohgiraffers.springlastteam.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupBuyingRepository groupBuyingRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private BuyingUserRepository buyingUserRepository;

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
