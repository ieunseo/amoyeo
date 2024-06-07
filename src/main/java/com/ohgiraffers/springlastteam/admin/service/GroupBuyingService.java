package com.ohgiraffers.springlastteam.admin.service;

import com.ohgiraffers.springlastteam.admin.repository.GroupBuyingRepository;
import com.ohgiraffers.springlastteam.entity.GroupBuying;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupBuyingService {
    @Autowired
    private GroupBuyingRepository groupBuyingRepository;

    public List<GroupBuying> getAllGroupBuyings() {
        return groupBuyingRepository.findAll();
    }

    public GroupBuying getGroupBuyingById(int buyingNo) {
        return groupBuyingRepository.findById(buyingNo).orElse(null);
    }

    public GroupBuying saveGroupBuying(GroupBuying groupBuying) {
        return groupBuyingRepository.save(groupBuying);
    }

    public void deleteGroupBuying(int buyingNo) {
        groupBuyingRepository.deleteById(buyingNo);
    }
}
