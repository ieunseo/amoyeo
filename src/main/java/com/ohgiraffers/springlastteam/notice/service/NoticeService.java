package com.ohgiraffers.springlastteam.notice.service;

import com.ohgiraffers.springlastteam.entity.Notice;
import com.ohgiraffers.springlastteam.entity.Users;
import com.ohgiraffers.springlastteam.notice.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.ohgiraffers.springlastteam.notice.repository.UserRepository;

import java.util.List;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository, @Qualifier("NoticeUserRepository") UserRepository userRepository) {
        this.noticeRepository = noticeRepository;
        this.userRepository = userRepository;
    }

    public List<Notice> findAllNotices() {
        return noticeRepository.findAll();
    }

    public Notice findNoticeById(int id) {
        return noticeRepository.findById(id).orElse(null);
    }

    public Notice saveNotice(String title, String content, String userId) {
        Users author = userRepository.findByUserId(userId);
        if (author != null && "Y".equals(author.getUserRights())) {
            Notice notice = new Notice(title, content, author);
            return noticeRepository.save(notice);
        }
        throw new IllegalArgumentException("관리자 권한이 없습니다.");
    }
}