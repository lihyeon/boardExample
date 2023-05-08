package com.tj.boardExample.controller;

import com.tj.boardExample.dto.BoardDto;
import com.tj.boardExample.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "board/board.html";
    }
    @RequestMapping(value = "/boardInsert", method = RequestMethod.GET)
    public String boardInsert(BoardDto boardDto, HttpSession session) {
        if (session.getAttribute("userKey") == null) {
            return "redirect:/loginPage";
        } else {
            boardDto.setUserKey((Integer) session.getAttribute("userKey"));
        }

        boardService.registerBoard(boardDto);
        return "redirect:/";
    }

    // 게시판 전체 조회하기(기본페이지: localhost:8081)
    @RequestMapping(value = "/", method = RequestMethod.GET) // Default = GET
    public String boardSelectAll(Model model) {
        List<BoardDto> boardDtoList = boardService.getAllBoard();
        model.addAttribute("boardList", boardDtoList);
        return "board/boardSelectAll";
        // boardSelect.html 에서 데이터 표현되도록
    }

    @RequestMapping("/boardSelect/{brdKey}") // Default = GET
    public String boardSelect(Model model, @PathVariable("brdKey") Integer brdKey2) {
        BoardDto boardDto = boardService.getBoard(brdKey2);
        model.addAttribute("board", boardDto);
        return "board/boardSelect";
        // boardSelect.html 에서 데이터 표현되도록
    }

    // 수정 전 정보를 조회하고 수정을 할 수 있는 페이지 return api
    @RequestMapping(value = "/boardUpdate/{brdKey}", method = RequestMethod.GET) // 수정 전 정보 조회
    public String boardUpdate(Model model, @PathVariable("brdKey") Integer brdKey2, HttpSession session) {
        if (session.getAttribute("userKey") == null) {
            return "redirect:/loginPage";
        }
        Integer sessionKey = (Integer) session.getAttribute("userKey");
        BoardDto boardDto = boardService.getBoard(brdKey2);
        if (boardDto.getUserKey().equals(sessionKey)) {
            model.addAttribute("board", boardDto);
            model.addAttribute("error", "");
            return "board/boardUpdate";
        } else {
            BoardDto boardDto2 = boardService.getBoard(brdKey2);
            model.addAttribute("board", boardDto2);
            model.addAttribute("error", "권한이 없습니다.");
            return "board/boardSelect";
        }
    }

    // 수정 버튼을 눌렀을때 데이터들을 통하여 실제 수정이 진행될 api
    @RequestMapping(value = "/boardUpdate2", method = RequestMethod.GET) // 수정 전 정보 조회
    public String boardUpdate2(Model model, BoardDto boardDto, HttpSession session) {
        if (session.getAttribute("userKey") == null) {
            return "redirect:/loginPage";
        } else {
            boardDto.setUserKey((Integer) session.getAttribute("userKey"));
        }
        if (boardService.modifyBoard(boardDto) == 1) {
            // 아래 주석 3줄을 이 코드로 표현할 수 있음(boardUpdate 를 참조)
            return "redirect:/boardSelect/" + boardDto.getBrdKey();
        } else {
            model.addAttribute("board", boardDto);
            model.addAttribute("error", "수정이 완료되지 못했습니다.");
            return "board/boardUpdate";
        }
//        BoardDto boardDto2 = boardService.getBoard(boardDto.getBrdKey());
//        model.addAttribute("board", boardDto2);
//        return "boardUpdate";
    }

    @RequestMapping(value = "/boardDelete/{brdKey}", method = RequestMethod.GET) // 수정 전 정보 조회
    public String boardDelete(Model model,@PathVariable("brdKey") Integer brdKey, HttpSession session) {
        if(session.getAttribute("userKey")==null) {
            return "redirect:/loginPage";
        }
        Integer sessionKey = (Integer) session.getAttribute("userKey");
        BoardDto boardDto = boardService.getBoard(brdKey);
        if(boardDto.getUserKey().equals(sessionKey)) {
            boardService.removeBoard(brdKey);
            return "redirect:/";
        } else {
            model.addAttribute("board", boardDto);
            model.addAttribute("error", "권한이 없습니다.");
            return "board/boardSelect";
        }
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