package com.ohgiraffers.springlastteam.mypage.service;

import com.ohgiraffers.springlastteam.entity.BuyingUserId;
import com.ohgiraffers.springlastteam.entity.GroupBuying;
import com.ohgiraffers.springlastteam.entity.Users;
import com.ohgiraffers.springlastteam.mypage.repository.MyPageBuyingUserRepository;
import com.ohgiraffers.springlastteam.mypage.repository.MyPageGroupBuyingRepository;
import com.ohgiraffers.springlastteam.mypage.repository.MypageUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MypageService {

    private final MyPageGroupBuyingRepository myPageGroupBuyingRepository;

    private final MypageUserRepository MypageUserRepository;

    private final MyPageBuyingUserRepository myPageBuyingUserRepository;

    public MypageService(MyPageGroupBuyingRepository myPageGroupBuyingRepository, com.ohgiraffers.springlastteam.mypage.repository.MypageUserRepository mypageUserRepository, MyPageBuyingUserRepository myPageBuyingUserRepository) {
        this.myPageGroupBuyingRepository = myPageGroupBuyingRepository;
        MypageUserRepository = mypageUserRepository;
        this.myPageBuyingUserRepository = myPageBuyingUserRepository;
    }


    @Transactional
    public void deleteById(int userNo, int buyingNo){
        System.out.println(userNo);
        System.out.println(buyingNo);
        GroupBuying groupBuying = myPageGroupBuyingRepository.findById(buyingNo)
                .orElseThrow(() -> new IllegalArgumentException("공동구매번호 삽입 안됨"));
        Users user = MypageUserRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("유저번호 삽입 안됨"));
        BuyingUserId buyingUserId = new BuyingUserId(groupBuying,user);
        myPageBuyingUserRepository.deleteById(buyingUserId);
    }
}
