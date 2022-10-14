package com.workshop.yuiman.service;

import com.workshop.yuiman.form.CreateInputForm;
import com.workshop.yuiman.dto.LoginInfoDto;
import com.workshop.yuiman.dto.UserInfoDto;
import com.workshop.yuiman.repository.LoginInfoRepository;
import com.workshop.yuiman.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
@Service
@RequiredArgsConstructor
public class LoginInfoService {

    private final LoginInfoRepository loginInfoRepository;
    private final UserInfoRepository userInfoRepository;

    Logger logger = LoggerFactory.getLogger(LoginInfoRepository.class);
    /**
     * <p><b>LoginInfoServiceのregistAccountメソッド</b></p>
     * <p>入力された新規ユーザ情報をDBに登録する</p>
     * @param createInputForm 新規登録入力情報
     * @return uid DBに自動登録されたuid
     * @version 1.0
     * */
    public int registAccount(CreateInputForm createInputForm){;
        //メールアドレスの存在チェック
        if(loginInfoRepository.checkMailaddress(createInputForm.getMailaddress())){
            logger.warn("入力されたメールアドレスは既に登録されています");
            return -1;
        }

        //t_login_infoに登録データの作成
        LoginInfoDto registData = new LoginInfoDto();
        registData.setMailaddress(createInputForm.getMailaddress());
        registData.setPassword(createInputForm.getPassword1());

        //パスワードを暗号化する処理

        //ユーザ情報を登録
        if(!loginInfoRepository.registAccount(registData)){
            logger.warn("ログイン情報登録に失敗");
            return -1;
        }
        //登録したユーザ情報のuidを取得
        int uid = loginInfoRepository.getUID(registData.getMailaddress());
        if(uid==-1){
            logger.warn("UID取得に失敗");
            return -1;
        }

        //t_user_infoにユーザ情報を登録
        UserInfoDto registInfo = new UserInfoDto();
        //登録したユーザ情報のuid
        registInfo.setUid(uid);
        registInfo.setName(createInputForm.getName());
        registInfo.setSlackName(createInputForm.getSlackname());
        //ユーザ種別は0:生徒
        registInfo.setTypeId(0);
        //認証可否は不可に設定する
        registInfo.setAuth(false);
        //初期値は0にする
        registInfo.setMovieTimes(0);
        registInfo.setReviewTimes(0);

        //初期ユーザ情報を登録
        if(!userInfoRepository.registInitUserInfo(registInfo)){
            logger.warn("ユーザ情報登録に失敗");
            return -1;
        }
        logger.info("ユーザ情報登録に成功:" + uid);
        return uid;

    }

    /**
     * <p><b>LoginInfoServiceのchkMailaddressメソッド</b></p>
     * <p>入力された新規ユーザ情報のメールアドレスがDBに存在しないか確認する</p>
     * @param createInputForm 新規登録入力情報
     * @return boolean true=存在する false=存在しない
     * @version 1.0
     * */
    public boolean chkMailaddress(CreateInputForm createInputForm){
        //メールアドレスの存在チェック
        if(loginInfoRepository.checkMailaddress(createInputForm.getMailaddress())){
            logger.warn("入力されたメールアドレスは既に登録されています");
            return true;
        }
        return false;
    }
}
