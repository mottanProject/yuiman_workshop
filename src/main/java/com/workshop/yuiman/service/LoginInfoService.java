package com.workshop.yuiman.service;

import com.workshop.yuiman.form.CreateInputForm;
import com.workshop.yuiman.dto.LoginInfoDto;
import com.workshop.yuiman.dto.UserInfoDto;
import com.workshop.yuiman.repository.LoginInfoRepository;
import com.workshop.yuiman.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginInfoService {

    private final LoginInfoRepository loginInfoRepository;
    private final UserInfoRepository userInfoRepository;

    /**
     * <p><b>LoginInfoServiceのregistAccountメソッド</b></p>
     * <p>入力された新規ユーザ情報をDBに登録する</p>
     * @param createInputForm 新規登録入力情報
     * @return uid DBに自動登録されたuid
     * @version 1.0
     * */
    public int registAccount(CreateInputForm createInputForm){;
        //t_login_infoに登録データの作成
        LoginInfoDto registData = new LoginInfoDto();
        registData.setMailaddress(createInputForm.getMailaddress());
        registData.setPassword(createInputForm.getPassword1());

        //パスワードを暗号化する処理

        //ユーザ情報を登録
        loginInfoRepository.registAccount(registData);
        //登録したユーザ情報のuidを取得
        int uid = loginInfoRepository.getUID(registData.getMailaddress());

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
        userInfoRepository.registInitUserInfo(registInfo);


        return uid;

    }
}
