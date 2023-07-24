package com.train.schoolplanhive.query.dao;


import com.train.schoolplanhive.query.model.EnrollPlanStatis;
import com.train.schoolplanhive.query.model.ProfessPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * create by xiuzhong.li at 2022/4/7
 *
 * @Description:文件描述：
 */

@Mapper
@Repository
public interface CoolMajorDao {

    @Select({"<script>",
            "select id,subject,province,school,year,major_code, sum(plan) as plan_total ,profess from enrollment_plan " ,
            "group by left(subject, 3) order by plan_total desc",
            "limit #{topn}",
            "</script>"
    })
    public List<EnrollPlanStatis> getCoolProfess(int topn);
}
