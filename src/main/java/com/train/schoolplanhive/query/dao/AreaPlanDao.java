package com.train.schoolplanhive.query.dao;


import com.train.schoolplanhive.query.model.AreaPlan;
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
public interface AreaPlanDao {

    @Select("select * from area_plan")
    public List<AreaPlan> getAllAreaPlan();
}
