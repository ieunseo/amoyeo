package com.ohgiraffers.springlastteam.gonggu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GroupBuyingDTO {
    private int buyingNo;

    private String buyingTitle;

    private String buyingText;

    private String buyingItem;

    private int userNo;

    public GroupBuyingDTO(int buyingNo, String buyingTitle, String buyingText, String buyingItem, int userNo) {
        this.buyingNo = buyingNo;
        this.buyingTitle = buyingTitle;
        this.buyingText = buyingText;
        this.buyingItem = buyingItem;
        this.userNo = userNo;
    }
}
