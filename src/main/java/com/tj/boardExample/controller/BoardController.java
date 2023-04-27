package com.tj.boardExample.controller;

import com.tj.boardExample.dto.BoardDto;
import com.tj.boardExample.wervice.BoardService;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/boardSelect/{brdKey}") // 슬래시를 통해 api 늘릴 수 있음/ 메소드 지정 안할시 Default = GET
    public String boardSelect(Model model, @PathVariable("brdKey") Integer brdKey){ // 경로변수
        BoardDto boardDto = boardService.getBoard(brdKey);
        model.addAttribute("board",boardDto);
        System.out.println(boardDto); // 잘 조회했는지 확인
        return "boardSelect.html";
        // boardSelect.html 에서 데이터 표현되도록
    }


    //    DTO        DTO     DTO
    // view controller service mapper = mapper.xml (db)
    //   --->       --->     --->     =
    //   <---       <---     <---
    //create, read, update (read+create), delete

    // view 화면단위
    // controller 제어단위
    // service 로직단위
    // mapper db 연동

}
