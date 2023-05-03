package com.tj.boardExample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto { // userDto 사용해도 상관없는데 전용 Dto 로 따로 만들어서 사용
    private String userId;
    private String userPw;
}
