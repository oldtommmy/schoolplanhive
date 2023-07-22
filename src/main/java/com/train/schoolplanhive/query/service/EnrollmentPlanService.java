package com.train.schoolplanhive.query.service;


import com.github.pagehelper.PageInfo;
import com.train.schoolplanhive.query.model.EnrollPlanStatis;
import com.train.schoolplanhive.query.model.EnrollmentPlan;

import java.util.List;

/**
 * create by xiuzhong.li at 2022/4/2
 *
 * @Description:文件描述：
 */
public interface EnrollmentPlanService {

    public PageInfo<EnrollmentPlan> getEnrollmentPlanList(int pageNo, int pageSize);
    public PageInfo<EnrollmentPlan> getEnrollmentPlanList(int pageNo,int PageSize,String school,
                                                          String profess,String province);
    public PageInfo<EnrollmentPlan> getEnrollmentPlanList(int pageNo,int PageSize,EnrollmentPlan queryCondition);

    /**
     *
     * @param school
     * @param province
     * @param isDesc 是否降序
     * @param topn
     * @return
     * 按学校和省份统计热门和冷门专业
     */
    public List<EnrollPlanStatis> getMajorStatis(String school, String province, boolean isDesc, int topn);

}
