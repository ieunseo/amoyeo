package com.ohgiraffers.springlastteam.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

// buyingPerson의 존재 이유가 없어 삭제합니다.
/* 이 테이블은 구매 신청한 유저의 신청 물품갯수와 신청날짜를 받습니다.*/
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class BuyingUser implements Serializable {
    @EmbeddedId
    private BuyingUserId id;

//    @Column(name = "buying_person")
//    private int buyingPerson;

    @Column(name = "buying_quantity")
    private int buyingQuantity;

    @Column(name = "buying_date")
    @Temporal(TemporalType.DATE)
    private Date buyingDate;

//    @MapsId("buyingNo")
//    @ManyToOne
//    @JoinColumn(name="buying_no")
//    private GroupBuying buyingNo;
//
//    @MapsId("userNo")
//    @ManyToOne
//    @JoinColumn(name="user_no")
//    private Users userNo;


    public BuyingUser(BuyingUserId id, int buyingQuantity, Date buyingDate) {
        this.id = id;
        this.buyingQuantity = buyingQuantity;
        this.buyingDate = buyingDate;
    }
}