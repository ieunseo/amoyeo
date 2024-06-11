package com.ohgiraffers.springlastteam.gonggu.service;


import com.ohgiraffers.springlastteam.entity.*;
import com.ohgiraffers.springlastteam.gonggu.dto.*;
import com.ohgiraffers.springlastteam.gonggu.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class DTOService {

    private final DTORepository dtoRepository;
    private final ModelMapper modelMapper;
    private final GroupBuyingRepository groupBuyingRepository;
    private final RequireBuyRepository requireBuyRepository;
    private final UserRepository userRepository;
    private final LikesRepository likesRepository;
    private final ImageRepository imageRepository;

    public DTOService(DTORepository dtoRepository, ModelMapper modelMapper, GroupBuyingRepository groupBuyingRepository, UserRepository userRepository, RequireBuyRepository requireBuyRepository, LikesRepository likesRepository, ImageRepository imageRepository) {
        this.dtoRepository = dtoRepository;
        this.modelMapper = modelMapper;
        this.groupBuyingRepository = groupBuyingRepository;
        this.userRepository = userRepository;
        this.likesRepository = likesRepository;
        this.requireBuyRepository = requireBuyRepository;
        this.imageRepository = imageRepository;
    }

    public List<GroupBuyingDTO> findGroupBuyingList() {
        List<GroupBuying> groupList = groupBuyingRepository.findAll();

        List<GroupBuyingDTO> groupBuyingDTOList = new ArrayList<>();

        for (GroupBuying groupBuying : groupList) {
            GroupBuyingDTO dto = modelMapper.map(groupBuying, GroupBuyingDTO.class);

            List<Image> imageList = imageRepository.findByGroupBuying(groupBuying);
            List<ImageDTO> imageDTOList = imageList.stream()
                    .map(image -> modelMapper.map(image,ImageDTO.class))
                    .collect(Collectors.toList());
            dto.setImageList(imageDTOList);
            groupBuyingDTOList.add(dto);
        }

        return groupBuyingDTOList;
    }

    public List<BuyingUserDTO> findBuyingUserList() {
        List<BuyingUser> buyingList = dtoRepository.findAll();

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
    public List<GroupBuyingDTO> searchGroupBuying(String query) {
        List<GroupBuying> searchResults = groupBuyingRepository.findByBuyingItemContainingIgnoreCase(query);
        return searchResults.stream()
                .map(group -> modelMapper.map(group, GroupBuyingDTO.class))
                .collect(Collectors.toList());
    }

    public List<RequireBuyDTO> searchRequireBuys(String query) {
        List<RequireBuy> searchResults = requireBuyRepository.findByRequireItemContainingIgnoreCase(query);
        return searchResults.stream()
                .map(requireBuy -> modelMapper.map(requireBuy, RequireBuyDTO.class))
                .collect(Collectors.toList());
    }
    public List<RequireBuyDTO> findRequireBuyList(int userNo) {
        List<RequireBuy> requireBuyList = requireBuyRepository.findAll();

        return requireBuyList.stream()
                .map(requireBuy -> {
                    RequireBuyDTO dto = modelMapper.map(requireBuy, RequireBuyDTO.class);
                    boolean liked = likesRepository.findByUserUserNoAndRequireBuyRequireNo(userNo, requireBuy.getRequireNo()).isPresent();
                    dto.setLiked(liked);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void addLike(int userNo, int requireNo) {
        Users user = userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("유저번호가 존재하지 않습니다."));
        RequireBuy requireBuy = requireBuyRepository.findById(requireNo)
                .orElseThrow(() -> new IllegalArgumentException("요청 구매 번호가 존재하지 않습니다."));

        Likes like = new Likes();
        like.setUser(user);
        like.setRequireBuy(requireBuy);
        likesRepository.save(like);
    }

    @Transactional
    public void removeLike(int userNo, int requireNo) {
        Users user = userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("유저번호가 존재하지 않습니다."));
        RequireBuy requireBuy = requireBuyRepository.findById(requireNo)
                .orElseThrow(() -> new IllegalArgumentException("요청 구매 번호가 존재하지 않습니다."));

        Optional<Likes> like = likesRepository.findByUserUserNoAndRequireBuyRequireNo(userNo, requireNo);
        like.ifPresent(likesRepository::delete);
    }

    public RequireBuyDTO findRequireBuyById(int requireNo) {
        RequireBuy requireBuy = requireBuyRepository.findById(requireNo)
                .orElseThrow(() -> new IllegalArgumentException("요청 구매 번호가 존재하지 않습니다."));
        return modelMapper.map(requireBuy, RequireBuyDTO.class);
    }

    @Transactional
    public void requestGroupBuying(BuyingUserDTO newBuyingUser) {
        GroupBuying groupBuying = groupBuyingRepository.findById(newBuyingUser.getBuyingNo())
                .orElseThrow(() -> new IllegalArgumentException("공동구매번호 삽입 안됨"));
        Users user = userRepository.findById(newBuyingUser.getUserNo())
                .orElseThrow(() -> new IllegalArgumentException("유저번호 삽입 안됨"));
        BuyingUserId buyingUserId = new BuyingUserId(groupBuying, user);
        BuyingUser buyingUser = new BuyingUser();
        buyingUser.setId(buyingUserId);

        buyingUser.setBuyingQuantity(newBuyingUser.getBuyingQuantity());
        buyingUser.setBuyingDate(newBuyingUser.getBuyingDate());

        System.out.println("서비스 : " + buyingUser);
        dtoRepository.save(buyingUser);
    }

    @Transactional
    public void deleteRequstBuying(Integer buyingNo, Integer userNo) {
        GroupBuying groupBuying = groupBuyingRepository.findById(buyingNo)
                .orElseThrow(() -> new IllegalArgumentException("공동구매번호 삽입 안됨"));
        Users user = userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("유저번호 삽입 안됨"));
        BuyingUserId buyingUserId = new BuyingUserId(groupBuying, user);
        dtoRepository.deleteById(buyingUserId);
    }
}