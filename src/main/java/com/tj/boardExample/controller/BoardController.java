package com.tj.boardExample.controller;

import com.tj.boardExample.dto.BoardDto;
import com.tj.boardExample.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelExtensionsKt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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

        return "board.html"; // templates 안의 경로에 맞춰서 이름 설정(templates 부터 경로 시작)
    }

    @RequestMapping("/boardSelect/{brdKey}") // Default = GET
    public String boardSelect(Model model, @PathVariable("brdKey") Integer brdKey2) {
        BoardDto boardDto = boardService.getBoard(brdKey2);
        model.addAttribute("board", boardDto);
        System.out.println(boardDto); // 잘 조회했는지 확인
        return "boardSelect";
        // boardSelect.html 에서 데이터 표현되도록
    }

    // 게시판 전체 조회하기
    @RequestMapping("/boardSelectAll}") // Default = GET
    public String boardSelectAll(Model model) {
        List<BoardDto> boardDtoList = boardService.getAllBoard();
        model.addAttribute("boardList", boardDtoList);
        return "boardSelectAll";
        // boardSelect.html 에서 데이터 표현되도록
    }

    // 수정 전 정보를 조회하고 수정을 할 수 있는 페이지 return api
    @RequestMapping(value = "/boardUpdate/{brdKey}", method = RequestMethod.GET) // 수정 전 정보 조회
    public String boardUpdate(Model model, @PathVariable("brdKey") Integer brdKey) {
        BoardDto boardDto = boardService.getBoard(brdKey);
        model.addAttribute("board", boardDto);
        System.out.println(boardDto);
        return "boardUpdate";
    }

    // 수정 버튼을 눌렀을때 데이터들을 통하여 실제 수정이 진행될 api
    @RequestMapping(value = "/boardUpdate2", method = RequestMethod.GET) // 수정 전 정보 조회
    public String boardUpdate2(Model model, BoardDto boardDto) {
        boardService.modifyBoard(boardDto);
//        BoardDto boardDto2 = boardService.getBoard(boardDto.getBrdKey());
//        model.addAttribute("board", boardDto2);
//        return "boardUpdate";
        return "redirect:/boardSelect/" + boardDto.getBrdKey(); // 위 3줄을 이 코드로 표현할 수도 있음(boardUpdate 를 참조)
    }

    @RequestMapping(value = "/boardDelete/{brdKey}", method = RequestMethod.GET) // 수정 전 정보 조회
    public String boardDelete(@PathVariable("brdKey") Integer brdKey) {
        // Integer brdKey = 1; // 1번 삭제 test
        boardService.removeBoard(brdKey);
        System.out.println("삭제");

        return "test.html"; // Select 에서 삭제 이후 test 로 가기
    }



}

//     DTO         DTO      DTO
// view  controller  service  mapper = mapper.xml (db)
//   --->         --->      --->     =
//   <---         <---      <---

// view 화면단위
// controller 제어단위
// service 로직단위/ 비즈니스 로직
// mapper db 연동

// Controller
// 생성과 수정을 진행하는 api 는 두가지로 이루어짐(insert, update)