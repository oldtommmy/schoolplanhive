package com.train.schoolplanhive.query.model;

import lombok.Data;



@Data
public class ProfessPlan {
    private Integer id;
    private Integer plan;       // 招生计划
    private String majorCode;   //专业代码
    private String profess;     //专业名称
}
