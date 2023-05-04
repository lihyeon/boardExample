package com.tj.boardExample.controller;

import com.tj.boardExample.dto.LoginDto;
import com.tj.boardExample.dto.UserDto;
import com.tj.boardExample.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
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

    // ** login logic
    // * loginPage 바인딩해주는 API
    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String loginPage() {
        return "user/loginPage";
    }
    // loginPage 에서 올라온 데이터를 가지고 실제 로그인이 진행될 API
    @RequestMapping(value = "/loginLogic", method = RequestMethod.GET)
    public String loginLogic(LoginDto loginDto, HttpSession session) {
        int result = userService.login(loginDto);
        if (result != 0) {
            session.setAttribute("userKey", result);
            // 로그인 로직
            // 항상
        } else {

        }
        return "user/loginPage";
    }
    @RequestMapping("/loginTest") // 이 경로로 들어오면 내가 받겠다. Spring 에게 알려준것
    public String testPage(Model model, HttpSession session) {
        System.out.println(session.getAttribute("userKey"));
        if (session.getAttribute("userKey") == null) {
            return "redirect:/loginPage";
        }

        model.addAttribute("test", "테스트");
        model.addAttribute("test2", "<a href=\"www.naver.com\">가</a>");
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        model.addAttribute("list", arrayList);
        return "test";
    }
}
