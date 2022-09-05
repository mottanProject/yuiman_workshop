package com.workshop.yuiman.repository;

import com.workshop.yuiman.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserInfoRepository {
    private final JdbcTemplate jdbcTemplate;
    /**
     * <p><b>LoginInfoRepositoryのregistInitUserInfoメソッド</b></p>
     * <p>新規ユーザの初期情報をDBに登録する</p>
     * @param userInfo ユーザ情報
     * @version 1.0
     * */
    public void registInitUserInfo(UserInfoDto userInfo) {
        jdbcTemplate.update(
                "INSERT INTO yuiman.t_user_info "
                        + "(uid, name, slackname, u_type_id, auth, movie_times. review_times) "
                        + "Values(?,?,?,?,?,?)",
                userInfo.getUid(),
                userInfo.getName(),
                userInfo.getSlackName(),
                userInfo.getTypeId(),
                userInfo.isAuth(),
                userInfo.getMovieTimes(),
                userInfo.getReviewTimes());
    }

    /**
     * <p><b>LoginInfoRepositoryのregistAccountメソッド</b></p>
     * <p>メールアドレスをキーにuidを取得する</p>
     * @param mailAddress メールアドレス
     * @return uid
     * @version 1.0
     * */
    //public int getUID(String mailAddress){
//        String sql = "SELECT uid, mailaddress FROM yuiman.t_login_info WHERE mailaddress = ?";
//        List<Map<String, Object>> resList = jdbcTemplate.queryForList(sql, mailAddress);
//        List<UserInfoDto> getList = new ArrayList<>();
//        for (Map<String, Object> info : resList) {
//            getList.add(new UserInfoDto(
//                    (int) info.get("uid"),
//                    (String) info.get("mailaddress")));
//        }
//
//        return getList.get(0).getUid();
   // }

}
