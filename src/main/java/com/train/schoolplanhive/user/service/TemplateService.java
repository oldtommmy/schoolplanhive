package com.train.schoolplanhive.user.service;

import com.train.schoolplanhive.user.model.SelectRecordDTO;

import java.util.HashMap;
import java.util.List;

public interface TemplateService {
    List<HashMap<String, Object>> queryTableDetailsByDate(SelectRecordDTO selectRecordDTO);

    Integer addRecord(HashMap<String, Object> map, String tableName);

    void updateRecord(HashMap<String, Object> map, String tableName, List<Integer> list);
}
