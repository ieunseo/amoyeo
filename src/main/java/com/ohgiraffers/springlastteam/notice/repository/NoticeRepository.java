package com.ohgiraffers.springlastteam.notice.repository;

import com.ohgiraffers.springlastteam.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
}
