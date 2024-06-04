package com.ohgiraffers.springlastteam.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BuyingUserId implements Serializable {

    @ManyToOne
    @JoinColumn(name="buying_no")
    private  GroupBuying buyingNo;

    @ManyToOne
    @JoinColumn(name="user_no")
    private Users userNo;

    public BuyingUserId(GroupBuying buyingNo, Users userNo) {
        this.buyingNo = buyingNo;
        this.userNo = userNo;
    }
}
