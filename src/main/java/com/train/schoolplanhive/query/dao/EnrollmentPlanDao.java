package com.train.schoolplanhive.query.dao;


import com.train.schoolplanhive.query.model.EnrollPlanStatis;
import com.train.schoolplanhive.query.model.EnrollmentPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * create by xiuzhong.li at 2022/4/1
 *
 * @Description:文件描述：招生计划持久层接口
 */
@Repository
@Mapper
public interface EnrollmentPlanDao {


    @Select("select * from enrollment_plan")
    public List<EnrollmentPlan> getEnrollmentPlansByPage();

    @Select({"<script>",
            "select * from enrollment_plan",
            "<where>",
            "<if test='school != null'>school like #{school}</if>",
            "<if test='subject != null'>and subject like #{subject}</if>",
            "<if test='province != null'>and province like #{province}</if>",
            "</where>",
            "</script>"

    })
    public List<EnrollmentPlan> getEnrollmentPlans(String school,String subject,String province);

    @Select({"<script>",
            "select * from enrollment_plan",
            "<where>",
            "<if test='school != null'>school like #{school}</if>",
            "<if test='subject != null'>and subject like #{subject}</if>",
            "<if test='province != null'>and province like #{province}</if>",
            "</where>",
            "ORDER BY convert(school USING gbk) COLLATE gbk_chinese_ci ASC",  // 添加按学校排序
            "</script>"
    })
    public List<EnrollmentPlan> getEnrollmentPlansGroupBySchool(String school,String subject,String province);

    @Select({"<script>",
            "select * from enrollment_plan",
            "<where>",
            "<if test='school != null'>school like #{school}</if>",
            "<if test='subject != null'>and subject like #{subject}</if>",
            "<if test='province != null'>and province like #{province}</if>",
            "</where>",
            "ORDER BY convert(subject USING gbk) COLLATE gbk_chinese_ci ASC",  // 添加按专业排序
            "</script>"
    })
    public List<EnrollmentPlan> getEnrollmentPlansGroupBySubject(String school,String subject,String province);


    @Select({"<script>",
            "select * from enrollment_plan",
            "<where>",
            "<if test='school != null'>school like #{school}</if>",
            "<if test='subject != null'>and subject like #{subject}</if>",
            "<if test='province != null'>and province like #{province}</if>",
            "</where>",
            "ORDER BY convert(province USING gbk) COLLATE gbk_chinese_ci ASC",  // 添加按省份排序
            "</script>"
    })
    public List<EnrollmentPlan> getEnrollmentPlansGroupByProvince(String school,String subject,String province);

    /**
     *  热门专业&冷门专业sql
     "select profess,sum(plan) as plantotal,school  from enrollment_plan ep
     group by major_code  order by  plantotal DESC limit 10";
     */
    @Select({"<script>",
            "select id,subject,province,school,year, sum(plan) as plan_total ,profess from enrollment_plan " ,
            "<where>",
            "<if test='school != null'>school = #{school}</if>",
            "<if test='province != null'>and province = #{province}</if>",
            "</where>",
            "group by subject order by plan_total ",
            "<if test='desc != null'>desc</if>",
            "<if test='desc == null'>asc</if>",
            "limit #{topn}",
            "</script>"
    })
    public List<EnrollPlanStatis> getTopNEnrollmentPlans(String school, String province, String desc, int topn);
}

