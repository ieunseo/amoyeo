package com.ohgiraffers.springlastteam.admin.repository;

import com.ohgiraffers.springlastteam.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("adminRepository")
public interface UserRepository extends JpaRepository<Users, Integer> {
}
