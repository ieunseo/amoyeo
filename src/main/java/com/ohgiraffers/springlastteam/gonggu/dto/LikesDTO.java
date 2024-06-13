package com.ohgiraffers.springlastteam.gonggu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LikesDTO {
    private int likeId;
    private int userNo;
    private int requireNo;
    private int likeCount;
    public LikesDTO(int likeId, int userNo, int requireNo, int likeCount) {
        this.likeId = likeId;
        this.userNo = userNo;
        this.requireNo = requireNo;
        this.likeCount = likeCount;
    }
}
