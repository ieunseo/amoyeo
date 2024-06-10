package com.ohgiraffers.springlastteam.admin.repository;

import com.ohgiraffers.springlastteam.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("adminImageRepository")
public interface ImageRepository extends JpaRepository<Image, Integer> {
}
