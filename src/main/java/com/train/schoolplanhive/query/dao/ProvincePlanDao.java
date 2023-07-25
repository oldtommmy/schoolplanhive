package com.train.schoolplanhive.query.dao;

import com.train.schoolplanhive.query.model.ProvincePlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ProvincePlanDao {

    @Select("select * from province_plan")
    public List<ProvincePlan> getAllProvincePlan();

}
