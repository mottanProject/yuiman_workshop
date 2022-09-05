package com.workshop.yuiman.form;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Objects;


// SetterとGetter、toString、equals、hashCodeを
// 生成するアノテーション
@Data
public class CreateInputForm {

    @NotBlank(message="ニックネームが入力されていません")
    private String name;

    @NotBlank(message="メールアドレスが入力されていません")
    @Email(message="メールアドレスの形式で入力してください")
    private String mailaddress;

    @Pattern(regexp="^[0-9a-zA-Z]*$", message="Slack名に不適切な文字が含まれています")
    private String slackname;

    @NotBlank(message="パスワードが入力されていません")
    @Size(min=8, max=20, message="パスワードは半角英数字8文字以上20文字以内に入力してください")
    @Pattern(regexp="^[0-9a-zA-Z]*$", message="パスワードは半角英数字で入力してください")
    private String password1;

    @NotBlank(message="パスワードが再入力されていません")
    @Size(min=8, max=20, message="パスワードは半角英数字8文字以上20文字以内に入力してください")
    @Pattern(regexp="^[0-9a-zA-Z]*$", message="パスワードは半角英数字で入力してください")
    private String password2;

    @AssertTrue(message="パスワードが一致しません")
    public boolean isSamePassword() {
        return Objects.equals(this.password1, this.password2);
    }


}