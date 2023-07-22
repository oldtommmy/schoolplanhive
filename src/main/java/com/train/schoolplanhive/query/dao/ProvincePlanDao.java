package com.train.schoolplanhive.query.dao;

import com.train.schoolplanhive.query.model.ProvincePlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * create by xiuzhong.li at 2022/4/7
 *
 * @Description:文件描述：各省份招生计划持久层
 */
@Mapper
@Repository
public interface ProvincePlanDao {

    @Select("select * from province_plan")
    public List<ProvincePlan> getAllProvincePlan();

}
