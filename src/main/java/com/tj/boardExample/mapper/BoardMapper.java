package com.tj.boardExample.mapper;

import com.tj.boardExample.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    void insertBoard(BoardDto boardDto);

    BoardDto selectBoard(Integer brdKey);

    List<BoardDto> selectBoardList();

    void updateBoard(BoardDto boardDto);

    void deleteBoard(Integer brdKey);
}
