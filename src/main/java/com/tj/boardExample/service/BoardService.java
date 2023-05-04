package com.tj.boardExample.service;

import com.tj.boardExample.dto.BoardDto;
import com.tj.boardExample.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired // 의존성 주입(연동)
    private BoardMapper boardMapper;

    public void registerBoard(BoardDto boardDto) { // insert-register
        boardMapper.insertBoard(boardDto);
    }

    public BoardDto getBoard(Integer brdKey){
        return boardMapper.selectBoard(brdKey);
    }

    public List<BoardDto> getAllBoard() {
        return boardMapper.selectBoardList();
    }

    public int modifyBoard(BoardDto boardDto) { // 이름 같은걸로 맞춰줘도됨 코드 복잡화를 위한것(update-modify)
        BoardDto boardDto1 = boardMapper.selectBoard(boardDto.getBrdKey());
        if(boardDto1.getBrdKey().equals(boardDto.getBrdKey())) {
            boardMapper.updateBoard(boardDto);
            return 1;
        } else {
            return 0;
        }
    }

    public void removeBoard(Integer brdKey) {

        boardMapper.deleteBoard(brdKey);

    }
}
