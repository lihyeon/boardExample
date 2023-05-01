package com.tj.boardExample.controller;

import com.tj.boardExample.dto.BoardDto;
import com.tj.boardExample.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/boardPage", method = RequestMethod.GET)
    public String boardPage() {
        // v -> c
        // c -> v
        return "board.html";
    }
    @RequestMapping(value = "/boardInsert", method = RequestMethod.GET)
    public String boardInsert(BoardDto boardDto){
        boardService.registerBoard(boardDto);
        return "board.html";
    }

    @RequestMapping("/boardSelect/{brdKey}") // Default=GET
    public String boardSelect(Model model, @PathVariable("brdKey") Integer brdKey2){
        BoardDto boardDto = boardService.getBoard(brdKey2);
        model.addAttribute("board", boardDto); 
        System.out.println(boardDto); // 잘 조회했는지 확인
        return "boardSelect"; // boardSelect.html 이라고 해도됨
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
