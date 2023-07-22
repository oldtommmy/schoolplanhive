package com.train.schoolplanhive.query.model;

import lombok.Data;

/**
 * create by xiuzhong.li at 2022/4/7
 *
 * @Description:文件描述：省份招生计划
 */
@Data
public class ProvincePlan {
    private Integer id;
    private Integer plan;       //招生计划
    private  String province;   //省份

}
