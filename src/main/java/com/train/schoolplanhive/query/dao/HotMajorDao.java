package com.train.schoolplanhive.query.dao;


import com.train.schoolplanhive.query.model.EnrollPlanStatis;
import com.train.schoolplanhive.query.model.ProfessPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface HotMajorDao {

    @Select({"<script>",
            "select id,subject,province,school,year,major_code, sum(plan) as plan_total ,profess from enrollment_plan " ,
            "group by left(subject, 3) order by plan_total asc",
            "limit #{topn}",
            "</script>"
    })
    public List<EnrollPlanStatis> getHotProfessPlan(int topn);
}
