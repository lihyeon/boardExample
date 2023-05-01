package com.tj.boardExample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Array;
import java.util.ArrayList;

@Controller // 이 클래스 파일이 실제로 돌아갈때 객체가 되면, controller 의 기능을 하게끔 spring 에게 알려줌
public class TestController {

    // 모델(데이터로직)M 제어C 화면V
    //   C <----- V
    //   C -----> V


    @RequestMapping("/") // 이 경로로 들어오면 내가 받겠다고 spring 에게 알려줌
    public String testPage(Model model) {
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
