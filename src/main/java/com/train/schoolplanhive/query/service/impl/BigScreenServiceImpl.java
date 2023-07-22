package com.train.schoolplanhive.query.service.impl;


import com.train.schoolplanhive.query.dao.AreaPlanDao;
import com.train.schoolplanhive.query.dao.CoolMajorDao;
import com.train.schoolplanhive.query.dao.HotMajorDao;
import com.train.schoolplanhive.query.dao.ProvincePlanDao;
import com.train.schoolplanhive.query.model.AreaPlan;
import com.train.schoolplanhive.query.model.ProfessPlan;
import com.train.schoolplanhive.query.model.ProvincePlan;
import com.train.schoolplanhive.query.service.BigScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by xiuzhong.li at 2022/4/7
 *
 * @Description:文件描述：
 */
@Service
public class BigScreenServiceImpl implements BigScreenService {
    @Autowired
    private AreaPlanDao areaPlanDao;
    @Autowired
    private CoolMajorDao coolMajorDao;
    @Autowired
    private HotMajorDao hotMajorDao;
    @Autowired
    private ProvincePlanDao provincePlanDao;

    @Override
    public List<ProfessPlan> getHotMajors(int topn) {
        return hotMajorDao.getHotProfessPlan(topn);
    }

    @Override
    public List<ProfessPlan> getCoolMajors(int topn) {
        return coolMajorDao.getCoolProfess(topn);
    }

    @Override
    public List<AreaPlan> getAllAreaPlan() {
        return areaPlanDao.getAllAreaPlan();
    }

    @Override
    public List<ProvincePlan> getAllProvincePlan() {
        return provincePlanDao.getAllProvincePlan();
    }
}
