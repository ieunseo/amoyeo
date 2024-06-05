package com.ohgiraffers.springlastteam.gonggu.repository;

import com.ohgiraffers.springlastteam.entity.GroupBuying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupBuyingRepository extends JpaRepository<GroupBuying, Integer> {
}
