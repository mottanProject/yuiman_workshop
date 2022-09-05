package com.workshop.yuiman.repository;

import com.workshop.yuiman.dto.LoginInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class LoginInfoRepository {
    private final JdbcTemplate jdbcTemplate;
    /**
     * <p><b>LoginInfoRepositoryのregistAccountメソッド</b></p>
     * <p>新規ユーザ情報をDBに登録する</p>
     * @param loginInfo 登録情報
     * @version 1.0
     * */
    public void registAccount(LoginInfoDto loginInfo) {
        jdbcTemplate.update(
                "INSERT INTO yuiman.t_login_info "
                        + "(mailaddress, password) "
                        + "Values(?,?)",
                loginInfo.getMailaddress(),loginInfo.getPassword());
    }

    /**
     * <p><b>LoginInfoRepositoryのregistAccountメソッド</b></p>
     * <p>メールアドレスをキーにuidを取得する</p>
     * @param mailAddress メールアドレス
     * @return uid
     * @version 1.0
     * */
    public int getUID(String mailAddress){
        String sql = "SELECT uid, mailaddress FROM yuiman.t_login_info WHERE mailaddress = ?";
        List<Map<String, Object>> resList = jdbcTemplate.queryForList(sql, mailAddress);
        List<LoginInfoDto> getList = new ArrayList<>();
        for (Map<String, Object> info : resList) {
            getList.add(new LoginInfoDto(
                    (int) info.get("uid"),
                    (String) info.get("mailaddress")));
        }

        return getList.get(0).getUid();
    }

}
