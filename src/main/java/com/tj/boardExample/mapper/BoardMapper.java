package com.tj.boardExample.mapper;

import com.tj.boardExample.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    void insertBoard(BoardDto boardDto);

    BoardDto selectBoard(Integer brdKey);

    void updateBoard(BoardDto boardDto);
}
