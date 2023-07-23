package com.train.schoolplanhive.query.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.train.schoolplanhive.query.dao.EnrollmentPlanDao;
import com.train.schoolplanhive.query.model.EnrollPlanStatis;
import com.train.schoolplanhive.query.model.EnrollmentPlan;
import com.train.schoolplanhive.query.service.EnrollmentPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(EnrollmentPlanServiceImpl.class);

    @Autowired
    private EnrollmentPlanDao enrollmentPlanDao;

    @Override
    public List<EnrollmentPlan> getAllPlanList() {
        return enrollmentPlanDao.getEnrollmentPlansByPage();
    }

    @Override
    public PageInfo<EnrollmentPlan> getEnrollmentPlanList(int pageNo, int pageSize) {

        PageHelper.startPage(pageNo,pageSize);  //只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页
        List<EnrollmentPlan> list = enrollmentPlanDao.getEnrollmentPlansByPage();
        PageInfo<EnrollmentPlan> pageInfo = new PageInfo<>(list);   // 用PageInfo对结果进行包装

        return pageInfo;
    }

    @Override
    public PageInfo<EnrollmentPlan> getEnrollmentPlanList(int pageNo, int pageSize, String school, String subject,
                                                          String province) {
        // System.out.println("param:school-profess-province");
        // System.out.println(school);
        // System.out.println(profess);
        // System.out.println(province);
        logger.info("school = {}, subject = {}, province = {}", school, subject, province);
        if(school != null && school.equals("")){
            school = null;
        }
        if(subject != null && subject.equals("")){
            subject = null;
        }
        if(province != null && province.equals("")){
            province = null;
        }
        PageHelper.startPage(pageNo,pageSize);  //只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页
        List<EnrollmentPlan> list = enrollmentPlanDao.getEnrollmentPlans(school, subject, province);
        PageInfo<EnrollmentPlan> pageInfo = new PageInfo<>(list);   // 用PageInfo对结果进行包装

        return pageInfo;
    }

    @Override
    public PageInfo<EnrollmentPlan> getEnrollmentPlanList(int pageNo, int PageSize, EnrollmentPlan queryCondition) {

        if(queryCondition == null){
            return this.getEnrollmentPlanList(pageNo,PageSize);
        }else{
            return this.getEnrollmentPlanList(pageNo,PageSize,queryCondition.getSchool(),
                     queryCondition.getSubject(),queryCondition.getProvince());
        }
    }

    @Override
    public PageInfo<EnrollmentPlan> getEnrollmentPlanGroupBySchool(int pageNo, int pageSize, EnrollmentPlan queryCondition) {

        if (queryCondition == null) {
            queryCondition = new EnrollmentPlan();
        }
        String school = queryCondition.getSchool();
        String subject = queryCondition.getSubject();
        String province = queryCondition.getProvince();
        if(school != null && school.equals("")){
            school = null;
        }
        if(subject != null && subject.equals("")){
            subject = null;
        }
        if(province != null && province.equals("")){
            province = null;
        }
        PageHelper.startPage(pageNo, pageSize);  //只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页
        List<EnrollmentPlan> list = enrollmentPlanDao.getEnrollmentPlansGroupBySchool(school, subject, province);
        PageInfo<EnrollmentPlan> pageInfo = new PageInfo<>(list);   // 用PageInfo对结果进行包装
        return pageInfo;
    }

    @Override
    public PageInfo<EnrollmentPlan> getEnrollmentPlanGroupBySubject(int pageNo, int pageSize, EnrollmentPlan queryCondition) {

        if (queryCondition == null) {
            queryCondition = new EnrollmentPlan();
        }
        String school = queryCondition.getSchool();
        String subject = queryCondition.getSubject();
        String province = queryCondition.getProvince();
        if(school != null && school.equals("")){
            school = null;
        }
        if(subject != null && subject.equals("")){
            subject = null;
        }
        if(province != null && province.equals("")){
            province = null;
        }
        PageHelper.startPage(pageNo, pageSize);  //只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页
        List<EnrollmentPlan> list = enrollmentPlanDao.getEnrollmentPlansGroupBySubject(school, subject, province);
        PageInfo<EnrollmentPlan> pageInfo = new PageInfo<>(list);   // 用PageInfo对结果进行包装
        return pageInfo;
    }

    @Override
    public PageInfo<EnrollmentPlan> getEnrollmentPlanGroupByProvince(int pageNo, int pageSize, EnrollmentPlan queryCondition) {

        if (queryCondition == null) {
            queryCondition = new EnrollmentPlan();
        }
        String school = queryCondition.getSchool();
        String subject = queryCondition.getSubject();
        String province = queryCondition.getProvince();
        if(school != null && school.equals("")){
            school = null;
        }
        if(subject != null && subject.equals("")){
            subject = null;
        }
        if(province != null && province.equals("")){
            province = null;
        }
        PageHelper.startPage(pageNo, pageSize);  //只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页
        List<EnrollmentPlan> list = enrollmentPlanDao.getEnrollmentPlansGroupByProvince(school, subject, province);
        PageInfo<EnrollmentPlan> pageInfo = new PageInfo<>(list);   // 用PageInfo对结果进行包装
        return pageInfo;
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
