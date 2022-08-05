package com.workshop.yuiman;

import java.util.List;
import com.workshop.yuiman.model.logininfoModel;
import com.workshop.yuiman.dao.logininfoDAO;

public class SelectLogininfo {
    public static void main(String[] args){
        //t_login_infoの全レコードを取得
        logininfoDAO lgDAO = new logininfoDAO();
        List<logininfoModel> lgList = lgDAO.findAll();

        for(logininfoModel list : lgList){
            System.out.println(list.getUid() + "," + list.getMailaddress() + "," + list.getPassword());
        }
    }
}
