package com.train.schoolplanhive.user.model;

import lombok.Data;


import java.util.HashMap;
import java.util.List;

@Data
public class AddRecordDTO {
    private String tableName;

//    @NotNull(message = "字段map不能为空")
    private HashMap<String,Object> map;

//    @NotEmpty(message = "所需要修改记录的id号不能为空")
    private List<Integer> list;
}
