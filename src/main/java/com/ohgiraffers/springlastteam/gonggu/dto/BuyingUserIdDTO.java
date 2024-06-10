package com.ohgiraffers.springlastteam.gonggu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BuyingUserIdDTO {
    private int buyingNo;

    private int userNo;

    public BuyingUserIdDTO(int buyingNo, int userNo) {
        this.buyingNo = buyingNo;
        this.userNo = userNo;
    }
}
