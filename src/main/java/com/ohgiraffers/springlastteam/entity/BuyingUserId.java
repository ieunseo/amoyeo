package com.ohgiraffers.springlastteam.entity;


import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

/* 임베더블 클래스입니다 복합키를 표현해줍니다.*/
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BuyingUserId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="buying_no")
    private  GroupBuying buyingNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_no")
    private Users userNo;

    public BuyingUserId(GroupBuying buyingNo, Users userNo) {
        this.buyingNo = buyingNo;
        this.userNo = userNo;
    }
}