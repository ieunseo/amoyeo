package com.ohgiraffers.springlastteam.notice.repository;

import com.ohgiraffers.springlastteam.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("NoticeUserRepository")
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUserId(String userId);
}
