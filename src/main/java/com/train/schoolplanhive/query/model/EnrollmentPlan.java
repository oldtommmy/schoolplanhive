package com.train.schoolplanhive.query.model;

import lombok.Data;

import java.util.Date;

/**
 * create by xiuzhong.li at 2022/4/1
 *
 * @Description:文件描述：招生计划条目实体
 */
@Data
public class EnrollmentPlan {

    private Integer id;
    private String school;
    private String schoolCode;//学校编码
    private String province;    //省份或直辖市
    private String majorCode;   //专业代码
    private String profess;     //专业名称
    private String subject;     //专业大类 1：文史 2：理工
    private Integer batch;          //录取批次
    private Integer plan;           //招生计划数
    private Date year;          //招生年份



}
