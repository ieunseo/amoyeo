package com.ohgiraffers.springlastteam.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

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