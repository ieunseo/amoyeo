package com.ohgiraffers.springlastteam.admin.service;

//import com.ohgiraffers.springlastteam.admin.repository.AdminRepository;
import com.ohgiraffers.springlastteam.admin.repository.BuyingUserRepository;
import com.ohgiraffers.springlastteam.admin.repository.GroupBuyingRepository;
import com.ohgiraffers.springlastteam.admin.repository.ImageRepository;
import com.ohgiraffers.springlastteam.admin.repository.UserRepository;
import com.ohgiraffers.springlastteam.entity.BuyingUser;
import com.ohgiraffers.springlastteam.entity.GroupBuying;
import com.ohgiraffers.springlastteam.entity.Image;
import com.ohgiraffers.springlastteam.entity.Users;
import com.ohgiraffers.springlastteam.gonggu.dto.GroupBuyingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ModelMapper modelMapper;

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

    public List<BuyingUser> findBuyingUsersByBuyingNo(GroupBuying buyingNo) {
        return buyingUserRepository.findByIdBuyingNo(buyingNo);
    }
    public GroupBuying findGroupBuyingById(int buyingNo) {
        return groupBuyingRepository.findById(buyingNo).orElseThrow(() -> new RuntimeException("GroupBuying not found"));
    }

    @Transactional
    public void registGroupBuying(GroupBuyingDTO newGroupBuying) {
        groupBuyingRepository.save(modelMapper.map(newGroupBuying, GroupBuying.class));
    }

    @Transactional
    public void registImages(List<Image> imageList) {
        imageRepository.save(modelMapper.map(imageList, Image.class));
    }
}

