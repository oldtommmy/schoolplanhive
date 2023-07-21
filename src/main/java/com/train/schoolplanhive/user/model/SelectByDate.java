package com.train.schoolplanhive.user.model;


import lombok.Data;

@Data
public class SelectByDate {
    private String field;
    private String startDate;
    private String endDate;
}
