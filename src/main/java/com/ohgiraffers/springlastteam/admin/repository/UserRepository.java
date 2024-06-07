package com.ohgiraffers.springlastteam.admin.repository;

import com.ohgiraffers.springlastteam.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
}
