package com.ohgiraffers.springlastteam.mypage.repository;

import com.ohgiraffers.springlastteam.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MypageUserRepository extends JpaRepository<Users, Integer> {
}
