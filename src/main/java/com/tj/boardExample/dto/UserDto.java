package com.tj.boardExample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Integer userKey;
    private String userName;
    private String userId;
    private String userPw;
    private Integer adminFlag; // 관리자 여부
}
