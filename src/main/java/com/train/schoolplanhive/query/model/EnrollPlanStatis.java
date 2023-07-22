package com.train.schoolplanhive.query.model;

import lombok.Data;

/**
 * create by xiuzhong.li at 2022/4/8
 *
 * @Description:文件描述：招生计划统计实体
 */

@Data
public class EnrollPlanStatis {
    private Integer id;
    private String profess; //专业
    private String majorCode;   //专业代码
    private Integer planTotal;  //计划汇总数量


}
