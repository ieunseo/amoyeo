package com.ohgiraffers.springlastteam.gonggu.service;

import com.ohgiraffers.springlastteam.entity.BuyingUser;
import com.ohgiraffers.springlastteam.entity.BuyingUserId;
import com.ohgiraffers.springlastteam.entity.GroupBuying;
import com.ohgiraffers.springlastteam.entity.Users;
import com.ohgiraffers.springlastteam.gonggu.dto.BuyingUserDTO;
import com.ohgiraffers.springlastteam.gonggu.dto.GroupBuyingDTO;
import com.ohgiraffers.springlastteam.gonggu.dto.UserDTO;
import com.ohgiraffers.springlastteam.gonggu.repository.DTORepository;
import com.ohgiraffers.springlastteam.gonggu.repository.GroupBuyingRepository;
import com.ohgiraffers.springlastteam.gonggu.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
//현재 DTO라 적혀있는 서비스와 레포지토리는  BuyingUser의 entity에 해당합니다.
@Service
public class DTOService {

    private final DTORepository dtoRepository;
    private final ModelMapper modelMapper;
    private final GroupBuyingRepository groupBuyingRepository;
    private final UserRepository userRepository;

    public DTOService(DTORepository dtoRepository, ModelMapper modelMapper, GroupBuyingRepository groupBuyingRepository, UserRepository userRepository) {
        this.dtoRepository = dtoRepository;
        this.modelMapper = modelMapper;
        this.groupBuyingRepository = groupBuyingRepository;
        this.userRepository = userRepository;
    }
    // user리스트 확인 가능 단 repository의 확장자의 제너릭을 Users로 바꿔줘야함
//    public List<UserDTO> finduserList() {
//        List<Users> userList = dtoRepository.findAll();
//
//        return userList.stream()
//                .map(users -> modelMapper.map(users,UserDTO.class))
//                .collect(Collectors.toList());
//    }

    public List<GroupBuyingDTO> findGroupBuyingList() {
        List<GroupBuying> groupList = groupBuyingRepository.findAll();


        return groupList.stream()
                .map(groups -> modelMapper.map(groups, GroupBuyingDTO.class))
                .collect(Collectors.toList());
    }
    // GroupBuying리스트 확인 가능 단 repository의 확장자의 제너릭을 GroupBuying로 바꿔줘야함
//    public List<GroupBuyingDTO> findGroupBuyingList() {
//        List<GroupBuying> groupList = dtoRepository.findAll();
//
//        return groupList.stream()
//                .map(groupBuying -> modelMapper.map(groupBuying, GroupBuyingDTO.class))
//                .collect(Collectors.toList());
//    }
    //복합키 리스트 출력
    public List<BuyingUserDTO> findBuyingUserList() {
        List<BuyingUser> buyingList = dtoRepository.findAll();
        for (BuyingUser buyingUser : buyingList) {
            System.out.println(buyingUser);
        }
        /*return buyingList.stream()
                .map(buyingUser -> modelMapper.map(buyingUser, BuyingUserDTO.class))
                .collect(Collectors.toList());*/

        /* embeddedId 클래스 복합키 값 가져오는 방법 */
        return buyingList.stream()
                .map(buyingUser -> {
                    BuyingUserDTO dto = modelMapper.map(buyingUser, BuyingUserDTO.class);
                    dto.setBuyingNo(buyingUser.getId().getBuyingNo().getBuyingNo());
                    dto.setUserNo(buyingUser.getId().getUserNo().getUserNo());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public GroupBuyingDTO findGroupBuyingById(int buyingNo) {
        GroupBuying groupBuying = groupBuyingRepository.findById(buyingNo)
                .orElseThrow(() -> new IllegalArgumentException("공동구매번호가 존재하지 않습니다."));
        return modelMapper.map(groupBuying, GroupBuyingDTO.class);
    }

    public UserDTO findUserById(int userNo) {
        Users user = userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("유저번호가 존재하지 않습니다."));
        return modelMapper.map(user, UserDTO.class);
    }
    //save
    @Transactional
    public void requestGroupBuying(BuyingUserDTO newBuyingUser) {
        //복합키를 담기위한 객체선언
        GroupBuying groupBuying = groupBuyingRepository.findById(newBuyingUser.getBuyingNo())
                .orElseThrow(() -> new IllegalArgumentException("공동구매번호 삽입 안됨"));
        Users user = userRepository.findById(newBuyingUser.getUserNo())
                .orElseThrow(() -> new IllegalArgumentException("유저번호 삽입 안됨"));
        //복합키를 Id에 담기
        BuyingUserId buyingUserId = new BuyingUserId(groupBuying, user);
        //BuyingUser 엔티티에 값 담기
        BuyingUser buyingUser = new BuyingUser();
        buyingUser.setId(buyingUserId);

        buyingUser.setBuyingQuantity(newBuyingUser.getBuyingQuantity());
        buyingUser.setBuyingDate(newBuyingUser.getBuyingDate());

        //저장
        System.out.println(buyingUser);
        dtoRepository.save(buyingUser);
        System.out.println("save 메소드 실행 후");
    }

    //delete
    @Transactional
    public void deleteRequstBuying(Integer buyingNo, Integer userNo) {
        //복합키를 담기위한 객체선언
        GroupBuying groupBuying = groupBuyingRepository.findById(buyingNo)
                .orElseThrow(() -> new IllegalArgumentException("공동구매번호 삽입 안됨"));
        Users user = userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("유저번호 삽입 안됨"));
        //복합키 Id에 담기
        BuyingUserId buyingUserId = new BuyingUserId(groupBuying, user);
        //삭제
        System.out.println(buyingUserId);
        System.out.println("삭제 전 서비스");
        dtoRepository.deleteById(buyingUserId);
        System.out.println("삭제 후 서비스");
    }


}