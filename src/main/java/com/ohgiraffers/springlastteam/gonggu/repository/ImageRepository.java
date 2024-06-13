package com.ohgiraffers.springlastteam.gonggu.repository;

import com.ohgiraffers.springlastteam.entity.GroupBuying;
import com.ohgiraffers.springlastteam.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public interface ImageRepository extends JpaRepository<Image, Integer> {

    List<Image> findByGroupBuying(GroupBuying groupBuying);
}
