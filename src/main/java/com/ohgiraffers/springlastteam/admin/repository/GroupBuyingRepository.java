package com.ohgiraffers.springlastteam.admin.repository;

import com.ohgiraffers.springlastteam.entity.GroupBuying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("adminGroupBuyingRepository")
public interface GroupBuyingRepository extends JpaRepository<GroupBuying, Integer> {
}
