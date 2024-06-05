package com.ohgiraffers.springlastteam.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/* 관리자와 일반 사용자 모두가 들어있는 테이블입니다 권한을 나눠서 하기에 유저와 관리자의 할수 있는 영역이 다릅니다.*/
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