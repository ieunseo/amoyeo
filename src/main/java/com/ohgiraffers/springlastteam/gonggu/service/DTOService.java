package com.ohgiraffers.springlastteam.gonggu.service;

import com.ohgiraffers.springlastteam.entity.BuyingUser;
import com.ohgiraffers.springlastteam.entity.GroupBuying;
import com.ohgiraffers.springlastteam.entity.Users;
import com.ohgiraffers.springlastteam.gonggu.dto.BuyingUserDTO;
import com.ohgiraffers.springlastteam.gonggu.dto.GroupBuyingDTO;
import com.ohgiraffers.springlastteam.gonggu.dto.UserDTO;
import com.ohgiraffers.springlastteam.gonggu.repository.DTORepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class DTOService {

    private final DTORepository dtoRepository;
    private final ModelMapper modelMapper;

    public DTOService(DTORepository dtoRepository, ModelMapper modelMapper) {
        this.dtoRepository = dtoRepository;
        this.modelMapper = modelMapper;
    }

    // user리스트 확인 가능 단 repository의 확장자의 제너릭을 Users로 바꿔줘야함
//    public List<UserDTO> finduserList() {
//        List<Users> userList = dtoRepository.findAll();
//
//        return userList.stream()
//                .map(users -> modelMapper.map(users,UserDTO.class))
//                .collect(Collectors.toList());
//    }

    // GroupBuying리스트 확인 가능 단 repository의 확장자의 제너릭을 GroupBuying로 바꿔줘야함
//    public List<GroupBuyingDTO> findGroupBuyingList() {
//        List<GroupBuying> groupList = dtoRepository.findAll();
//
//        return groupList.stream()
//                .map(groupBuying -> modelMapper.map(groupBuying, GroupBuyingDTO.class))
//                .collect(Collectors.toList());
//    }

    public List<BuyingUserDTO> findBuyingUserList() {
        List<BuyingUser> buyingList = dtoRepository.findAll();

        return buyingList.stream()
                .map(buyingUser -> modelMapper.map(buyingUser, BuyingUserDTO.class))
                .collect(Collectors.toList());
    }
}
