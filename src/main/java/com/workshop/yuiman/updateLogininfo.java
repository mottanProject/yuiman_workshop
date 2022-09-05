package com.workshop.yuiman;

import java.util.List;
import com.workshop.yuiman.model.logininfoModel;
import com.workshop.yuiman.dao.logininfoDAO;

public class updateLogininfo {
    public static void main(String[] args){
        //更新する情報を設定
        int uid = 1; //メールアドレス
        String mail = "a@mail.com"; //メールアドレス
        String pass = "aaa"; //パスワード

        logininfoDAO lgDAO = new logininfoDAO();
        //INSERT
        logininfoModel upinfo = new logininfoModel(uid, mail,pass);

        //SQLが実行されたか判定
        if(lgDAO.updateInfo(upinfo)) {
            System.out.println("更新しました");
        }else{
            System.out.println("更新できませんでした");
        }


    }



}
