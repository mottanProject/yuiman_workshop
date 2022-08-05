package com.workshop.yuiman.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.workshop.yuiman.model.logininfoModel;

public class logininfoDAO {
    //接続設定情報
    private String url = "jdbc:postgresql://localhost:5432/workshopdb";
    private String user = "postgres";
    private String password = "hogehoge";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rset = null;

    //全件検索してリストに格納
    public List<logininfoModel> findAll(){
        List<logininfoModel> loginlist = new ArrayList<>();
        try{
            //workshopdbへ接続
            conn = DriverManager.getConnection(url, user, password);

            //SELECT文の設定
            stmt = conn.createStatement();
            String sql = "SELECT * FROM yuiman.t_login_info";

            //SELECT文の実行
            rset = stmt.executeQuery(sql);

            //SELECT結果の受け取り
            while(rset.next()){
                String uid = rset.getString("uid");
                String mail = rset.getString("mailaddress");
                String pass = rset.getString("password");
                logininfoModel logininfo = new logininfoModel(uid,mail,pass);
                loginlist.add(logininfo);
            }
        }
        catch (SQLException e){
                e.printStackTrace();
            }
        finally {
                try {
                    if(rset != null)rset.close();
                    if(stmt != null)stmt.close();
                    if(conn != null)conn.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }

            }
        return loginlist;
    }

    public boolean createInfo(logininfoModel newinfo){
        //入力値を取得
        String mail = newinfo.getMailaddress();
        String pass = newinfo.getPassword();
        try{
            //workshopdbへ接続
            conn = DriverManager.getConnection(url, user, password);

            //自動コミットをOFFにする
            conn.setAutoCommit(false);

            //SELECT文の設定
            stmt = conn.createStatement();
            String sql = "INSERT INTO yuiman.t_login_info (mailaddress, password) VALUES (?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            //値の挿入
            pStmt.setString(1,mail);
            pStmt.setString(2,pass);

            //INSERT文の実行
            pStmt.executeUpdate();

            //コミット
            conn.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        finally {
            try {
                if(rset != null)rset.close();
                if(stmt != null)stmt.close();
                if(conn != null)conn.close();
            }
            catch (SQLException e){
                e.printStackTrace();
                return false;
            }

        }
        return true;
    }
}

