package com.ohgiraffers.springlastteam.gonggu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GroupBuyingDTO {
    private int buyingNo;

    private String buyingText;

    private String buyingItem;

    private int userNo;

    private String buyingQuality;

    private int buyingPrice;

    private String userName;

    private List<ImageDTO> imageList;

    public GroupBuyingDTO(int buyingNo, String buyingText, String buyingItem, int userNo, String buyingQuality, int buyingPrice, String userName, List<ImageDTO> imageList) {
        this.buyingNo = buyingNo;
        this.buyingText = buyingText;
        this.buyingItem = buyingItem;
        this.userNo = userNo;
        this.buyingQuality = buyingQuality;
        this.buyingPrice = buyingPrice;
        this.userName = userName;
        this.imageList = imageList;
    }
}
