package com.ohgiraffers.springlastteam.gonggu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RequireBuyDTO {

    private int requireNo;
    private String requireText;
    private String requireItem;
    private int requirePrice;
    private String requireQuality;
    private int userNo;
    private boolean liked;
    private String userName;
    private int likeCount; // likeCount 속성 추가

    public RequireBuyDTO(int requireNo, String requireText, String requireItem, int requirePrice, String requireQuality, int userNo, boolean liked, String userName, int likeCount) {
        this.requireNo = requireNo;
        this.requireText = requireText;
        this.requireItem = requireItem;
        this.requirePrice = requirePrice;
        this.requireQuality = requireQuality;
        this.userNo = userNo;
        this.liked = liked;
        this.userName = userName;
        this.likeCount = likeCount;
    }
}
