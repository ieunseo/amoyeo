package com.ohgiraffers.springlastteam.mypage.repository;

import com.ohgiraffers.springlastteam.entity.GroupBuying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("myPageGroupBuyingRepository")
public interface MyPageGroupBuyingRepository extends JpaRepository<GroupBuying, Integer> {
    List<GroupBuying> findByUser_UserNo(int userNo); // 수정: findByUser_UserNo 로 변경
}
