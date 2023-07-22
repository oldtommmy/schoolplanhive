package com.train.schoolplanhive.query.dao;


import com.train.schoolplanhive.query.model.ProfessPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * create by xiuzhong.li at 2022/4/7
 *
 * @Description:文件描述：热门专业持久层
 */
@Mapper
@Repository
public interface HotMajorDao {

    @Select("select * from hot_major order by plan asc limit #{topn}")
    public List<ProfessPlan> getHotProfessPlan(int topn);
}
