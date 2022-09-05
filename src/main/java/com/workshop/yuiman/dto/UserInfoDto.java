package com.workshop.yuiman.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserInfoDto {
    private int uid;
    private String name;
    private String slackName;
    private int typeId;
    private boolean auth;
    private LocalDateTime joinDate;
    private String profileImgPath;
    private int movieTimes;
    private int reviewTimes;
    private String sheetUrl;

    public UserInfoDto(){}



}
