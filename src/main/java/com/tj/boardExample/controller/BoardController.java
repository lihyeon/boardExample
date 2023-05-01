package com.tj.boardExample.controller;

import com.tj.boardExample.dto.BoardDto;
import com.tj.boardExample.service.BoardService;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping("/boardPage")
    public String boardPage() {
        // v -> c
        // c -> v

        return "board.html";
    }
    @RequestMapping("/boardInsert")
    public String boardInsert(BoardDto boardDto){
        System.out.println(boardDto.getBrdTitle());
        System.out.println(boardDto.getBrdContent());

        return "board.html";
    }

    @RequestMapping("/boardSelect")
    public String boardSelect(){
        BoardDto boardDto = boardService.getBoard(1);
        System.out.println(boardDto); // 잘 조회했는지 확인
        return "boardSelect.html";
        // boardSelect.html에서 데이터 표현되도록
    }


    //    DTO        DTO     DTO
    // view controller service mapper = mapper.xml (db)
    //   --->       --->     --->     =
    //   <---       <---     <---

    // view 화면단위
    // controller 제어단위
    // service 로직단위
    // mapper db 연동

}
