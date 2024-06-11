package com.ohgiraffers.springlastteam.gonggu.repository;

import com.ohgiraffers.springlastteam.entity.RequireBuy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequireBuyRepository extends JpaRepository<RequireBuy, Integer> {
    List<RequireBuy> findByRequireItemContainingIgnoreCase(String query);
}
