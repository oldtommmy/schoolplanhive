package com.train.schoolplanhive.user.model;


import lombok.Data;

import java.util.HashMap;

@Data
public class SelectRecordDTO {
    private String tableName;
    private HashMap<String,Object> map;
    private HashMap<String,Object> fuzzyQueryMap;
    private SelectByDate date;

    private Integer pageNum = 1;
    private Integer pageSize = 1000;
}
