package com.train.schoolplanhive.query.service;

import com.github.pagehelper.PageInfo;
import com.train.schoolplanhive.query.model.AreaPlan;

import java.util.List;


/**
 * create by tommychan at 2023/7/23
 *
 * @Description:
 */
public interface AreaPlanService {

    public List<AreaPlan> getAreaPlan();
}
