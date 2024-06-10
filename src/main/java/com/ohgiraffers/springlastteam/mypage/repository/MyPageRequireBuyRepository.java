package com.ohgiraffers.springlastteam.mypage.repository;

import com.ohgiraffers.springlastteam.entity.RequireBuy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("myPageRequireBuyRepository")
public interface MyPageRequireBuyRepository extends JpaRepository<RequireBuy, Integer> {
    List<RequireBuy> findByUser_UserNo(int userNo);
}