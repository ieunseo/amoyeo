package com.ohgiraffers.springlastteam.login.repository;

import com.ohgiraffers.springlastteam.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserIdAndUserPwd(String userId, String userPwd);
    boolean existsByUserId(String userId);

}