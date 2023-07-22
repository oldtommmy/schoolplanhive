package com.train.schoolplanhive.query.model;

import lombok.Data;

/**
 * create by xiuzhong.li at 2022/4/7
 *
 * @Description:文件描述：专业招生计划
 */

@Data
public class ProfessPlan {
    private Integer id;
    private Integer plan;       // 招生计划
    private String majorCode;   //专业代码
    private String profess;     //专业名称
}
