package com.train.schoolplanhive.user.dao;


import com.train.schoolplanhive.user.model.SelectByDate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface TemplateDao {
    List<HashMap<String, Object>> queryTableDetailsByDate(String tableName, HashMap<String, Object> map, SelectByDate date, HashMap<String, Object> fuzzyQueryMap);

    Integer addRecord(@Param("map") HashMap<String,Object> map, @Param("tableName") String tableName);

    Integer selectLastInsertID();

    void updateRecord(@Param("map") HashMap<String,Object> map,@Param("tableName") String tableName,@Param("list") List<Integer> list);
}
