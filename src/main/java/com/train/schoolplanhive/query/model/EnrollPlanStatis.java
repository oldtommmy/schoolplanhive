package com.train.schoolplanhive.query.model;

import lombok.Data;



@Data
public class EnrollPlanStatis {
    private Integer id;
    private String profess; //专业
    private String school;  //学校
    private String province;    //省份或直辖市
    private String majorCode;   //专业代码
    private String Subject;    //专业
    private Integer planTotal;  //计划汇总数量
    private Integer year; //年份
}
