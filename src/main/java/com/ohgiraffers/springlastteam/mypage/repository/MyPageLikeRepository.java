package com.ohgiraffers.springlastteam.mypage.repository;

import com.ohgiraffers.springlastteam.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mypageLikeRepository")
public interface MyPageLikeRepository extends JpaRepository<Likes, Integer> {
    List<Likes> findByUser_UserNo(int userNo);
    Likes findByUser_UserNoAndRequireBuy_RequireNo(int userNo, int requireNo);
}
