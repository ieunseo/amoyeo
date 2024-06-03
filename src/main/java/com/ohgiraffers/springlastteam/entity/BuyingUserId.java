package com.ohgiraffers.springlastteam.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BuyingUserId implements Serializable {

    @Column(name = "buying_no")
    private int buyingNo;

    @Column(name = "user_no")
    private int userNo;
}
