package com.train.schoolplanhive.query.service;

import com.train.schoolplanhive.query.model.*;

import java.util.List;

/**
 * create by xiuzhong.li at 2022/4/7
 *
 * @Description:文件描述：
 */
public interface BigScreenService {

    //获取热门专业
    public List<EnrollPlanStatis> getHotMajors(int topn);
    //获取冷门专业
    public List<EnrollPlanStatis> getCoolMajors(int topn);
    //区域招生计划
    public List<AreaPlan> getAllAreaPlan();
    //省份招生计划
    public List<ProvincePlan> getAllProvincePlan();


}
