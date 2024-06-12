package com.ohgiraffers.springlastteam.notice.repository;

import com.ohgiraffers.springlastteam.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
}
