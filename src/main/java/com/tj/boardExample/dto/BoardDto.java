package com.tj.boardExample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto { // data transfer object 데이터 가방

    private Integer brdKey;
    private String brdTitle;
    private String brdContent;
    protected Integer userKey;



}
