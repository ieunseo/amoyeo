package com.ohgiraffers.springlastteam.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private int userNo;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_pwd", nullable = false)
    private String userPwd;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_apartment")
    private String userApartment;

    @Column(name = "user_apart_num")
    private String userApartNum;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_rights")
    private String userRights;

    public Users(Integer userNo, String userId, String userPwd, String userPhone, String userApartment, String userApartNum, String userName, String userRights) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userPhone = userPhone;
        this.userApartment = userApartment;
        this.userApartNum = userApartNum;
        this.userName = userName;
        this.userRights = userRights;
    }
}
