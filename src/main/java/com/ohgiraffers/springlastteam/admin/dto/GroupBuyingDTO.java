package com.ohgiraffers.springlastteam.admin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GroupBuyingDTO {
    private int buyingNo;

    private String buyingText;

    private String buyingItem;

    private String buyingQuality;

    private int buyingPrice;

    private int userNo;

    public GroupBuyingDTO(int buyingNo, String buyingText, String buyingItem, String buyingQuality, int buyingPrice, int userNo) {
        this.buyingNo = buyingNo;
        this.buyingText = buyingText;
        this.buyingItem = buyingItem;
        this.buyingQuality = buyingQuality;
        this.buyingPrice = buyingPrice;
        this.userNo = userNo;
    }
}
