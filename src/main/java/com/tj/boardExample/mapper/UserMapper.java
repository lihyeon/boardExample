package com.tj.boardExample.mapper;


import com.tj.boardExample.dto.LoginDto;
import com.tj.boardExample.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void insertUser(UserDto userDto);

    UserDto selectUser(Integer userKey);

    List<UserDto> selectUserList();

    void updateUser(UserDto userDto);

    void deleteUser(UserDto userDto);
    
    // 로그인 로직
    UserDto loginUser(LoginDto loginDto);
}
