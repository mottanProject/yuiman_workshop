package com.workshop.yuiman.repository;

import com.workshop.yuiman.dto.LoginInfoDto;
import com.workshop.yuiman.common.LoadProperties;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
@RequiredArgsConstructor
public class LoginInfoRepository {
    //JdbcTemplate
    private final JdbcTemplate jdbcTemplate;
    //設定ファイル読込
    private final LoadProperties loadProperties = new LoadProperties();
    //クエリ定義名定数
    private final String QUE_INSERT_LOGIN_INFO="T_LOGIN_INFO.INSERT.LOGIN_INFO";
    private final String QUE_GET_UID="T_LOGIN_INFO.GET.UID";
    private final String QUE_CNT_MAIL="T_LOGIN_INFO.CNT.MAIL";
    //カラム定義名定数
    private final String COL_UID="uid";
    private final String COL_MAIL_ADDRESS="mailaddress";
    private final String COL_PASSWORD="password";
    private final String COL_LOGINDATE="login_date_last";

    Logger logger = LoggerFactory.getLogger(LoginInfoRepository.class);
    /**
     * <p><b>LoginInfoRepositoryのregistAccountメソッド</b></p>
     * <p>新規ユーザ情報をDBに登録する</p>
     * @param loginInfo 登録情報
     * @return boolean 処理結果：true=成功 false=失敗
     * @version 1.0
     * */
    public boolean registAccount(LoginInfoDto loginInfo) {
        try {
            jdbcTemplate.update(loadProperties.getQuery(QUE_INSERT_LOGIN_INFO),
                    loginInfo.getMailaddress(), loginInfo.getPassword());
        }catch (DuplicateKeyException e){
            //一意制約エラー
            logger.warn("入力されたメールアドレスは既に登録されています");
            return false;
        }catch (DataAccessException e2){
            //DB接続エラー
            logger.warn("DB接続エラー");
            e2.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * <p><b>LoginInfoRepositoryのregistAccountメソッド</b></p>
     * <p>メールアドレスをキーにuidを取得する</p>
     * @param mailAddress メールアドレス
     * @return uid DBで生成されたUID(-1:処理異常)
     * @version 1.0
     * */
    public int getUID(String mailAddress){
        String sql = loadProperties.getQuery(QUE_GET_UID);
        List<LoginInfoDto> getList = new ArrayList<>();
        try {
            List<Map<String, Object>> resList = jdbcTemplate.queryForList(sql, mailAddress);
            for (Map<String, Object> info : resList) {
                getList.add(new LoginInfoDto(
                        (int) info.get(COL_UID),
                        (String) info.get(COL_MAIL_ADDRESS)));
            }
        }catch (DataAccessException e){
            e.printStackTrace();
            return -1;
        }

        return getList.get(0).getUid();
    }
    /**
     * <p><b>LoginInfoRepositoryのcheckMailaddressメソッド</b></p>
     * <p>メールアドレスがDBに存在するかチェックする</p>
     * @param mailAddress メールアドレス
     * @return boolean 処理結果：true=存在する false=存在しない
     * @version 1.0
     * */
    public boolean checkMailaddress(String mailAddress){
        String sql = loadProperties.getQuery(QUE_CNT_MAIL);
        boolean isExist = false;
        try {
            List<Map<String, Object>> resList = jdbcTemplate.queryForList(sql, mailAddress);
            for (Map<String, Object> info : resList) {
                isExist = (boolean) info.get("exists");
            }
        }catch (DataAccessException e){
            e.printStackTrace();
            return true;
        }

        return isExist;
    }

}
