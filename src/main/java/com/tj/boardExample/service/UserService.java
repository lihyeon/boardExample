package com.tj.boardExample.service;


import com.tj.boardExample.dto.BoardDto;
import com.tj.boardExample.dto.LoginDto;
import com.tj.boardExample.dto.UserDto;
import com.tj.boardExample.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void registerUser(UserDto userDto) { // insert-register
        userMapper.insertUser(userDto);
    }

    public UserDto getUser(Integer userKey) {
        return userMapper.selectUser(userKey);
    }

    public List<UserDto> getUserAll() {
        return userMapper.selectUserList();
    }

    public void modifyUser(UserDto userDto) {
        userMapper.updateUser(userDto);
    }

    public void removeUser(UserDto userDto) {
        userMapper.deleteUser(userDto);
    }

    // login
    public int login(LoginDto loginDto) {
        UserDto userDto = userMapper.loginUser(loginDto);
        if (userDto.getUserPw().equals(loginDto.getUserPw())) {
            return userDto.getUserKey();
        } else {
            return 0;
        }
    }

}
