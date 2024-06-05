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

    private String buyingQuality;

    private int buyingPrice;

    public GroupBuyingDTO(int buyingNo, String buyingTitle, String buyingText, String buyingItem, int userNo, String buyingQuality, int buyingPrice) {
        this.buyingNo = buyingNo;
        this.buyingTitle = buyingTitle;
        this.buyingText = buyingText;
        this.buyingItem = buyingItem;
        this.userNo = userNo;
        this.buyingQuality = buyingQuality;
        this.buyingPrice = buyingPrice;
    }
}
