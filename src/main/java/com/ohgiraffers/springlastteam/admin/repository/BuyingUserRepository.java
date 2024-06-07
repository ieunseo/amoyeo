package com.ohgiraffers.springlastteam.admin.repository;

import com.ohgiraffers.springlastteam.entity.BuyingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("adminBuyingUserRepository")
public interface BuyingUserRepository extends JpaRepository<BuyingUser, Integer> {
}
