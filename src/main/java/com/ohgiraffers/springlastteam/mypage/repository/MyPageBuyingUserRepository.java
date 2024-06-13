package com.ohgiraffers.springlastteam.mypage.repository;

import com.ohgiraffers.springlastteam.entity.BuyingUser;
import com.ohgiraffers.springlastteam.entity.BuyingUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("myPageBuyingUserRepository")
public interface MyPageBuyingUserRepository extends JpaRepository<BuyingUser, Integer> {
    List<BuyingUser> findById_UserNo_UserNo(int userNo);

    void deleteById(BuyingUserId buyingUserId);
}