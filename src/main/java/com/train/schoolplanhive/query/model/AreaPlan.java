package com.train.schoolplanhive.query.model;

import lombok.Data;

/**
 * create by xiuzhong.li at 2022/4/7
 *
 * @Description:文件描述：区域招生计划
 */
@Data
public class AreaPlan {

    private Integer id;
    private Integer plan;   //招生计划
    private String area;    //招生区域

}
