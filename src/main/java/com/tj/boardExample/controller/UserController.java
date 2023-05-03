package com.tj.boardExample.controller;

import com.tj.boardExample.dto.UserDto;
import com.tj.boardExample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 1. INSERT(register) 회원 등록
    @RequestMapping(value = "/userInsertPage", method = RequestMethod.GET)
    public String useInsertPage() {
        return "user/userInsert.html";
    }
    @RequestMapping(value = "/userInsertLogic", method = RequestMethod.GET)
    public String userInsertLogic(UserDto userDto){
        userService.registerUser(userDto);

        return "user/userInsert.html"; // templates 안의 경로에 맞춰서 이름 설정(templates 부터 경로 시작)
    }

    // 2. SELECT(get) / SELECT ALL(get all) 회원 조회
    @RequestMapping(value = "/userSelect/{userKey}", method = RequestMethod.GET)
    public String userSelect(Model model, @PathVariable("userKey") Integer userKey) {
        UserDto userDto = userService.getUser(userKey);
        System.out.println(userDto);
        model.addAttribute("user",userDto);
        return "user/userSelect";
    }
    @RequestMapping(value = "/userSelectAll", method = RequestMethod.GET)
    public String userSelectAll(Model model){
        List<UserDto> userDtoList = userService.getUserAll();
        model.addAttribute("userList", userDtoList);
        return "user/userSelectAll";
    }
    
    // 3. UPDATE(modify) 회원 정보 수정
    // 3-1. 회원 정보 조회하고 수정할 수 있는 페이지
    @RequestMapping(value = "/userUpdate/{userKey}", method = RequestMethod.GET)
    public String userUpdate(Model model, @PathVariable("userKey") Integer userKey) {
        UserDto userDto = userService.getUser(userKey);
        model.addAttribute("user", userDto);
        return "user/userUpdate";
    }
    // 3-2. 실제 수정이 진행될 페이지
    @RequestMapping(value = "/userUpdate2", method = RequestMethod.GET)
    public String userUpdate2(Model model, UserDto userDto) {
        userService.modifyUser(userDto);
        return "redirect:/userSelect" + userDto.getUserKey();
    }
    
    // 4. DELETE(remove) 회원 삭제
    @RequestMapping(value = "/userDelete/{userKey}", method = RequestMethod.GET)
    public String userDelete(Model model, UserDto userDto) {
        userService.removeUser(userDto);

        return "user/userSelectAll";
    }
}
