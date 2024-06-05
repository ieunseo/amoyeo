package com.ohgiraffers.springlastteam.gonggu.repository;

import com.ohgiraffers.springlastteam.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
}
