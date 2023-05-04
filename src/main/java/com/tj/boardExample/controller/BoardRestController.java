package com.tj.boardExample.controller;

import com.tj.boardExample.dto.BoardDto;
import com.tj.boardExample.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Controller + ResponseBody: 데이터 로직만 실행가능(html 없이) - MVC model2
@RequestMapping("/board")

public class BoardRestController { //REST ful API 구조

    @Autowired
    private BoardService boardService;

    @GetMapping
    public List<BoardDto> testApi() {
        return boardService.getAllBoard();
    }

    @GetMapping(value = "/{brdKey}")
    public BoardDto testApi(@PathVariable("brdKey") Integer brdKey) {
        return boardService.getBoard(brdKey);
    }

    @PostMapping // postman 으로 데이터 넣기
    public void insertBoard(BoardDto boardDto) {
        boardService.registerBoard(boardDto);
    }

    // postman 을 통한 수정 api
    @PutMapping
    public void updateBoard(BoardDto boardDto) {
        boardService.modifyBoard(boardDto);
    }

    @DeleteMapping(value = "/{brdKey}")
    public void deleteBoard(@PathVariable("brdKey") Integer brdKey) {
        boardService.removeBoard(brdKey);
    }

}


