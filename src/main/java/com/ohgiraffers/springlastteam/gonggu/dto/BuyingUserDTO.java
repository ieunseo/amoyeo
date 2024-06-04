package com.ohgiraffers.springlastteam.gonggu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BuyingUserDTO {
    private int buyingNo;

    private int userNo;

    private int buyingPerson;

    private int buyingQuantity;

    private Date buyingDate;

    public BuyingUserDTO(int buyingNo, int userNo, int buyingPerson, int buyingQuantity, Date buyingDate) {
        this.buyingNo = buyingNo;
        this.userNo = userNo;
        this.buyingPerson = buyingPerson;
        this.buyingQuantity = buyingQuantity;
        this.buyingDate = buyingDate;
    }
}
