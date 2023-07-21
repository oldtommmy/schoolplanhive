package com.train.schoolplanhive.user.service.impl;

import com.train.schoolplanhive.user.model.SelectRecordDTO;
import com.train.schoolplanhive.user.dao.TemplateDao;
import com.train.schoolplanhive.user.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TemplateServiceImpl  implements TemplateService {

    @Autowired
    private TemplateDao templateDao;

    @Override
    public List<HashMap<String, Object>> queryTableDetailsByDate(SelectRecordDTO selectRecordDTO) {

        return templateDao.queryTableDetailsByDate(selectRecordDTO.getTableName(),selectRecordDTO.getMap(),selectRecordDTO.getDate(),
                selectRecordDTO.getFuzzyQueryMap());
    }

    @Override
    public Integer addRecord(HashMap<String, Object> map, String tableName) {
        templateDao.addRecord(map,tableName);
        return (templateDao.selectLastInsertID());
    }


    @Override
    public void updateRecord(HashMap<String, Object> map, String tableName, List<Integer> list) {
        templateDao.updateRecord(map,tableName,list);
    }
}
