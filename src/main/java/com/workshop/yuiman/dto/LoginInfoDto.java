package com.workshop.yuiman.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LoginInfoDto {
    private int uid;
    private String mailaddress;
    private String password;
    private Date loginDate;

    public LoginInfoDto(){}

    public LoginInfoDto(String mailaddress, String password){
        this.mailaddress = mailaddress;
        this.password = password;
    }

    public LoginInfoDto(Integer uid, String mailaddress){
        this.uid = uid;
        this.mailaddress = mailaddress;
        this.password = password;
    }

    public LoginInfoDto(Integer uid, String mailaddress, String password){
        this.uid = uid;
        this.mailaddress = mailaddress;
        this.password = password;
    }

    public LoginInfoDto(Integer uid, String mailaddress, String password, Date logindate){
        this.uid = uid;
        this.mailaddress = mailaddress;
        this.password = password;
        this.loginDate = logindate;
    }
}
