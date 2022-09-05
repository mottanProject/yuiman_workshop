package com.workshop.yuiman.model;
import java.sql.Timestamp;

public class logininfoModel {

    private int uid;
    private String mailaddress;
    private String password;
    private Timestamp loginDate;

    public logininfoModel(Integer uid, String mailaddress, String password){
        this.uid = uid;
        this.mailaddress = mailaddress;
        this.password = password;
    }
    public logininfoModel(String mailaddress, String password){
        this.mailaddress = mailaddress;
        this.password = password;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getMailaddress() {
        return mailaddress;
    }

    public void setMailaddress(String mailaddress) {
        this.mailaddress = mailaddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Timestamp loginDate) {
        this.loginDate = loginDate;
    }

}
