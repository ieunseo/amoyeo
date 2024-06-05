package com.ohgiraffers.springlastteam.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class BuyingUser implements Serializable {
    @EmbeddedId
    private BuyingUserId id;

    @Column(name = "buying_person")
    private int buyingPerson;

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

    public BuyingUser(BuyingUserId id, int buyingPerson, int buyingQuantity, Date buyingDate) {
        this.id = id;
        this.buyingPerson = buyingPerson;
        this.buyingQuantity = buyingQuantity;
        this.buyingDate = buyingDate;
    }
}