package com.ohgiraffers.springlastteam.login.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
    private int userNo;

    private String userId;

    private String userPwd;

    private String userPhone;

    private String userApartment;

    private String userApartNum;

    private String userName;

    private String userRights;

    public UserDTO(int userNo, String userId, String userPwd, String userPhone, String userApartment, String userApartNum, String userName, String userRights) {
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