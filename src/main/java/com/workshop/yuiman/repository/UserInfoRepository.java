package com.workshop.yuiman.repository;

import com.workshop.yuiman.common.LoadProperties;
import com.workshop.yuiman.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserInfoRepository {
    //JdbdTemplate
    private final JdbcTemplate jdbcTemplate;
    //設定ファイル読込
    private final LoadProperties loadProperties = new LoadProperties();
    //クエリ定義名定数
    private final String QUE_INSERT_USER_INFO="T_USER_INFO.INSERT.USER_INFO";
    //カラム定義名定数
    private final String COL_UID="uid";
    private final String COL_NAME="name";
    private final String COL_SLACKNAME="slackname";
    private final String COL_UTYPEID="u_type_id";
    private final String COL_AUTH="auth";
    private final String COL_JOIN_DATE="join_date";
    private final String COL_GRADUATE_DATE="graduate_date";
    private final String COL_PROFILE_PICTURE_PATH="profile_picture_path";
    private final String COL_MOVIE_TIMES="movie_times";
    private final String COL_REVIEW_TIMES="reviews_times";
    private final String COL_SHEET_URL="sheet_url";

    Logger logger = LoggerFactory.getLogger(LoginInfoRepository.class);
    /**
     * <p><b>LoginInfoRepositoryのregistInitUserInfoメソッド</b></p>
     * <p>新規ユーザの初期情報をDBに登録する</p>
     * @param userInfo ユーザ情報
     * @return boolean 処理結果：true=成功 false=失敗
     * @version 1.0
     * */
    public boolean registInitUserInfo(UserInfoDto userInfo) {
        try{
            jdbcTemplate.update(loadProperties.getQuery(QUE_INSERT_USER_INFO),
                    userInfo.getUid(),
                    userInfo.getName(),
                    userInfo.getSlackName(),
                    userInfo.getTypeId(),
                    userInfo.isAuth(),
                    userInfo.getJoinDate(),
                    userInfo.getGraduateDate(),
                    userInfo.getProfileImgPath(),
                    userInfo.getMovieTimes(),
                    userInfo.getReviewTimes());
        }catch (DuplicateKeyException e){
            //一意制約エラー
            logger.warn("UIDが重複しているため登録できません");
            return false;
        }catch (DataAccessException e2){
            //DB接続エラー
            logger.warn("DB接続エラー");
            e2.printStackTrace();
            return false;
        }

        return true;

    }

}
