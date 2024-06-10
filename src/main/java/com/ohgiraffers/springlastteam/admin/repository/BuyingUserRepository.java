package com.ohgiraffers.springlastteam.admin.repository;

import com.ohgiraffers.springlastteam.entity.BuyingUser;
import com.ohgiraffers.springlastteam.entity.GroupBuying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("adminBuyingUserRepository")
public interface BuyingUserRepository extends JpaRepository<BuyingUser, Integer> {
    List<BuyingUser> findByIdBuyingNo(GroupBuying buyingNo);
}
