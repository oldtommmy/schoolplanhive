package com.train.schoolplanhive.query.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.train.schoolplanhive.query.dao.EnrollmentPlanDao;
import com.train.schoolplanhive.query.model.EnrollPlanStatis;
import com.train.schoolplanhive.query.model.EnrollmentPlan;
import com.train.schoolplanhive.query.service.EnrollmentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by xiuzhong.li at 2022/4/2
 *
 * @Description:文件描述：招生计划操作及查询
 */
@Service
public class EnrollmentPlanServiceImpl implements EnrollmentPlanService {

    @Autowired
    private EnrollmentPlanDao enrollmentPlanDao;

    @Override
    public PageInfo<EnrollmentPlan> getEnrollmentPlanList(int pageNo, int pageSize) {

        PageHelper.startPage(pageNo,pageSize);  //只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页
        List<EnrollmentPlan> list = enrollmentPlanDao.getEnrollmentPlansByPage();
        PageInfo<EnrollmentPlan> pageInfo = new PageInfo<>(list);   // 用PageInfo对结果进行包装

        return pageInfo;
    }

    @Override
    public PageInfo<EnrollmentPlan> getEnrollmentPlanList(int pageNo, int pageSize, String school, String profess,
                                                          String province) {
        System.out.println("param:school-profess-province");
        System.out.println(school);
        System.out.println(profess);
        System.out.println(province);
        if(school != null && school.equals("")){
            school = null;
        }
        if(profess != null && profess.equals("")){
            profess = null;
        }
        if(province != null && province.equals("")){
            province = null;
        }
        PageHelper.startPage(pageNo,pageSize);  //只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页
        List<EnrollmentPlan> list = enrollmentPlanDao.getEnrollmentPlans(school, profess, province);
        PageInfo<EnrollmentPlan> pageInfo = new PageInfo<>(list);   // 用PageInfo对结果进行包装

        return pageInfo;
    }

    @Override
    public PageInfo<EnrollmentPlan> getEnrollmentPlanList(int pageNo, int PageSize, EnrollmentPlan queryCondition) {

        if(queryCondition == null){
            return this.getEnrollmentPlanList(pageNo,PageSize);
        }else{
            return this.getEnrollmentPlanList(pageNo,PageSize,queryCondition.getSchool(),
                     queryCondition.getProfess(),queryCondition.getProvince());
        }

    }

    @Override
    public List<EnrollPlanStatis> getMajorStatis(String school, String province, boolean isDesc, int topn) {
        List<EnrollPlanStatis> enrollPlanStatisList = null;
        if (isDesc){
            enrollPlanStatisList = enrollmentPlanDao.getTopNEnrollmentPlans(school,province,"desc",topn);
        }else{
            enrollPlanStatisList = enrollmentPlanDao.getTopNEnrollmentPlans(school,province,null,topn);
        }

        return enrollPlanStatisList;
    }
}
