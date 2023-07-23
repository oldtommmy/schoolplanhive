package com.train.schoolplanhive.query.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.train.schoolplanhive.query.dao.AreaPlanDao;
import com.train.schoolplanhive.query.model.AreaPlan;
import com.train.schoolplanhive.query.model.EnrollmentPlan;
import com.train.schoolplanhive.query.service.AreaPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by tommychan at 2023/7/23
 *
 * @Description:
 */
@Service
public class AreaServicePlanImpl implements AreaPlanService {

    @Autowired
    AreaPlanDao areaPlanDao;

    @Override
    public List<AreaPlan> getAreaPlan() {
        return areaPlanDao.getAllAreaPlan();
    }
}
