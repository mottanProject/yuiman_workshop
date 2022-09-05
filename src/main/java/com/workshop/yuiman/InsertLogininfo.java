package com.workshop.yuiman;

import java.util.List;
import com.workshop.yuiman.model.logininfoModel;
import com.workshop.yuiman.dao.logininfoDAO;

public class InsertLogininfo {
    public static void main(String[] args){
        //追加する情報を設定
        String mail = "g@mail.com"; //メールアドレス
        String pass = "ggg"; //パスワード

        logininfoDAO lgDAO = new logininfoDAO();
        //INSERT
        logininfoModel newinfo = new logininfoModel(mail,pass);

        //SQLが実行されたか判定
        if(lgDAO.createInfo(newinfo)) {
            System.out.println("追加しました");
        }else{
            System.out.println("追加できませんでした");
        }


    }



}
