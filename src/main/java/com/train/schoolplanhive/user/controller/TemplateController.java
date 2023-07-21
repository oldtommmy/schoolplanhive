package com.train.schoolplanhive.user.controller;


import com.train.schoolplanhive.user.model.AddRecordDTO;
import com.train.schoolplanhive.user.model.SelectRecordDTO;
import com.train.schoolplanhive.user.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/bigdataTemplate")
public class TemplateController {


    @Autowired
    private TemplateService templateService;


    @PostMapping("/queryTableDetails")
    public List<HashMap<String, Object>> queryTableDetails(@RequestBody SelectRecordDTO selectRecordDTO){

        //通过日期筛选
        //if (selectRecordDTO.getDate() != null){
        return templateService.queryTableDetailsByDate(selectRecordDTO);
        //        }
        //        return Result.OK(landingTemplateFirstService.queryTableDetails(selectRecordDTO));
    }


    @PostMapping("/addRecord")
    public Integer  addRecord(@RequestBody @Validated AddRecordDTO addRecordDTO){

        String tableName = addRecordDTO.getTableName();
        HashMap<String,Object> map = addRecordDTO.getMap();

//        if (map.isEmpty()){
//            return Result.error("map不能为空");
//        }


        return templateService.addRecord(map,tableName);
    }


    @PostMapping("/updateRecord")
    public String updateRecord(@RequestBody @Validated AddRecordDTO addRecordDTO){

        String tableName = addRecordDTO.getTableName();
        HashMap<String,Object> map = addRecordDTO.getMap();
        if (map == null || map.isEmpty()){
            return "word is empty";
        }

        templateService.updateRecord(map,addRecordDTO.getTableName(),addRecordDTO.getList());
        return "修改成功";
    }





}
