package com.tj.boardExample.service;

import com.tj.boardExample.dto.BoardDto;
import com.tj.boardExample.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired // 의존성 주입(연동)
    private BoardMapper boardMapper;

    public void registerBoard(BoardDto boardDto) {
        boardMapper.insertBoard(boardDto);
    }

    public BoardDto getBoard(Integer brdKey){
        return boardMapper.selectBoard(brdKey);
    }
}
